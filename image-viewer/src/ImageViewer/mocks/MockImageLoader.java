package ImageViewer.mocks;

import ImageViewer.Presenter.Image;
import ImageViewer.Presenter.ImageLoader;

public class MockImageLoader implements ImageLoader {
    private final String[] idsimg = new String[] {"0","1","2"};
    @Override
    public Image load() {
        return imagepositions(0);
    }

    private Image imagepositions(int i) {
        return new Image() {
            @Override
            public String id() {
                return idsimg[i];
            }

            @Override
            public Image next() {
                return imagepositions((i + 1) % idsimg.length);
            }

            @Override
            public Image prev() {
                return imagepositions(i > 0 ? i -1 : idsimg.length-1);
            }
        };
    }
}
