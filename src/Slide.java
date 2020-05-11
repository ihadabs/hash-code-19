import java.util.ArrayList;

public class Slide {

    ArrayList<Photo> photos = new ArrayList<>(3);


    void addPhoto(Photo photo) {
        photos.add(photo);
    }

    void removeAllPhotos() {
        photos = new ArrayList<>(3);
    }

    boolean isFull() {
        int content = 0;
        for (Photo photo: photos)
            content += photo.hORv;
        return content == 2;
    }

}
