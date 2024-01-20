package ImageViewer.swing;

import ImageViewer.Presenter.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private int initShift;
    private List<Paint> imagenes = new ArrayList<>();

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initShift);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initShift);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    @Override
    public void paint(String id, int offset) {
        imagenes.add(new Paint(id, offset));
        repaint();
    }

    private static final Map<String,ImageIcon> colors = Map.of(
            "0", new ImageIcon("image-viewer/src/ImageViewer/Imagenestrabajo/Bichon-maltes-Becker-2-640x961.jpg"),
            "1", new ImageIcon("image-viewer/src/ImageViewer/Imagenestrabajo/bichon-maltes-razas.png"),
            "2",new ImageIcon( "image-viewer/src/ImageViewer/Imagenestrabajo/diferencias_entre_bichon_maltes_y_coton_de_tulear_44857_600_square.jpg")
    );

    @Override
    public void clear() {
        imagenes.clear();
    }


    @Override
    public void paint(Graphics g) {
        for (Paint img : imagenes) {
            g.drawImage(colors.get(img.id).getImage(),0,0,
                    colors.get(img.id).getIconWidth(),
                    colors.get(img.id).getIconHeight(),
                    this);

        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    private record Paint(String id, int offset) {
    }
}
