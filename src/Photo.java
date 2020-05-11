import java.util.*;

public class Photo implements Comparable<Photo> {
    int number;
    int hORv;
    boolean l8 = false;
    ArrayList<String> tags;
    String string = "";

    public Photo(int number, int hORv, ArrayList<String> tags) {
        this.number = number;
        this.hORv = hORv;

        Collections.sort(tags);

        this.string = (hORv == 2) ? "H" : "V";

        for (String string: tags) {
            this.string += string;
        }

        this.tags = tags;
    }

    @Override
    public String toString() {
        String tags = "";
        for(String tag: this.tags)
            tags += " " + tag;

        return ((hORv == 2) ? "H" : "V") + " " + this.tags.size() + tags;
    }

    public int doWork(Photo photo) {
        ArrayList<String> a = tags;
        ArrayList<String> b = photo.tags;

        ArrayList<String> inter = intersection(a, b);

        int inA = a.size() - inter.size();
        int inB = b.size() - inter.size();
        int inI = inter.size();

        int min = Math.min(inA, Math.min(inB, inI));


        return min;
    }



//    public ArrayList<String> union(ArrayList<String> list1, ArrayList<String> list2) {
//        Set<String> set = new HashSet<>();
//
//        set.addAll(list1);
//        set.addAll(list2);
//
//        return new ArrayList<>(set);
//    }

    public ArrayList<String> intersection(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> list = new ArrayList<>();

        for (String t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    @Override
    public int compareTo(Photo o) {
        return string.compareTo(o.string);
    }
}
