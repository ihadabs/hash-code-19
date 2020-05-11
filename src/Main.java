import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Main {



    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> names = new ArrayList<>(7);
        names.add("a_example.txt");
        names.add("b_lovely_landscapes.txt");
        names.add("c_memorable_moments.txt");
        names.add("d_pet_pictures.txt");
        names.add("e_shiny_selfies.txt");

        int i = 1;
        for (String name: names) {
            System.out.println(name);

            PrintWriter printWriter = new PrintWriter(new FileOutputStream(i+".txt"));

            ArrayList<Slide> slides = solve(name);
            printWriter.println(slides.size());

            for (Slide slide: slides) {
                for (Photo photo : slide.photos)
                    printWriter.print(photo.number + " ");
                printWriter.println();
            }

            printWriter.close();
            i++;
        }
    }

    public static Scanner read(String fileName) throws FileNotFoundException {
        return new Scanner(new FileInputStream(fileName));
    }

    public static ArrayList<Photo> build(String fileName) throws FileNotFoundException {
        Scanner scanner = read(fileName);

        int numberOfPhotos = scanner.nextInt();
        ArrayList<Photo> photos = new ArrayList<>(numberOfPhotos+1);

        for (int i=0; i<numberOfPhotos; i++) {

            // H == 2 && V == 1
            int hORv = scanner.next().equals("H") ? 2 : 1;

            int numberOfTags = scanner.nextInt();
            ArrayList<String> tags = new ArrayList<>(numberOfTags+1);
            for (int t=0; t<numberOfTags; t++)
                tags.add(scanner.next());

            photos.add(new Photo(i, hORv, tags));
        }

        return photos;
    }

    public static ArrayList<Photo> sort(ArrayList<Photo> photos) {
        Collections.sort(photos);

//        ArrayList<Photo> out = new ArrayList<>(photos.size()+1);
//
//        while (!photos.isEmpty()) {
//            System.out.println(photos.size());
//            Photo photo = photos.remove(0);
//            out.add(photo);
//
//            for (int i=0; i<photos.size(); i++) {
//                Photo iphoto = photos.get(i);
//
//                int dodo = photo.doWork(iphoto);
//                int min = (int) Math.min(Math.floor(photo.tags.size()/2.0), Math.floor(iphoto.tags.size()/2.0));
//
//                if (dodo >= min) {
//                    out.add(photos.remove(i));
//                }
//            }
//        }

        return photos;
    }


    public static ArrayList<Slide> solve(String fileName) throws FileNotFoundException {

        ArrayList<Photo> photos = build(fileName);
        photos = sort(photos);

        Stack<Photo> holder = new Stack<>();

        ArrayList<Slide> slides = new ArrayList<>(photos.size()+1);


        while (!photos.isEmpty()) {
            Photo photo = photos.remove(0);

            if (photo.hORv == 2) {
                Slide tempSlide = new Slide();
                tempSlide.addPhoto(photo);
                slides.add(tempSlide);


            } else if (!holder.isEmpty()) {
                Slide tempSlide = new Slide();
                tempSlide.addPhoto(photo);
                tempSlide.addPhoto(holder.pop());
                slides.add(tempSlide);


            } else {
                holder.add(photo);
            }
        }

        if (!holder.isEmpty()) {
            Slide tempSlide = new Slide();
            tempSlide.addPhoto(holder.pop());
            slides.add(tempSlide);
        }

        return slides;
    }
}
