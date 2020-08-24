package app.API.DisplayVideo;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class DisplayVideo {
    public  static void MediaFiles(AnchorPane anchorpanel, MediaView lift_video, int value) throws IOException {

        DoubleProperty width = lift_video.fitWidthProperty();
        DoubleProperty height = lift_video.fitHeightProperty();
        lift_video.setPreserveRatio(false);
        lift_video.setSmooth(true);
        width.bind(Bindings.selectDouble(anchorpanel.sceneProperty(), "width").subtract(150));
        height.bind(Bindings.selectDouble(anchorpanel.sceneProperty(), "height").subtract(150));
        Media media = new Media(new File("./src/main/resources/MediaAnimations/Drone/"+value+".mp4").toURI().toURL().toString());
        javafx.scene.media.MediaPlayer player = new javafx.scene.media.MediaPlayer(media);
        lift_video.setMediaPlayer(player);
        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
        player.play();
    }
}
/*

        new Thread(this).start();
 */