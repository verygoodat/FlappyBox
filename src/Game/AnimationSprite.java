package Game;

import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class AnimationSprite extends Transition{

    private final ImageView imageview;
    private final int count;
    private final int columns;
    private final int offsetx;
    private final int offsety;
    private final int width;
    private final int height;

    public AnimationSprite(ImageView imageview,
                           int count,
                           Duration duration,
                           int columns,
                           int offsetx,
                           int offsety,
                           int width,
                           int height)
    {
        this.imageview = imageview;
        this.count = count;
        this.columns = columns;
        this.offsetx = offsetx;
        this.offsety = offsety;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
    }
    @Override
    protected void interpolate(double v) {
        final int index = Math.min((int) Math.floor(v*count), count-1);
        final int x = (index % columns) * width + offsetx;
        final int y = (index / columns) * height + offsety;
        imageview.setViewport(new Rectangle2D(x,y,width,height));
    }
}
