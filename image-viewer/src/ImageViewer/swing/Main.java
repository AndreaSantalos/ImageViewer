package ImageViewer.swing;

import ImageViewer.Presenter.ImagePresenter;
import ImageViewer.mocks.MockImageLoader;
import ImageViewer.Presenter.Image;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay());
        presenter.show(image());
        frame.setVisible(true);
    }

    private static Image image() {
        return new MockImageLoader().load();
    }
}
