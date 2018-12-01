import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args){
        final String DATABASE_NAME = "sleep-me";
        final String ACCT_PATH = "sleep-me.json";

        Firebase db = new Firebase(DATABASE_NAME, ACCT_PATH);



        UI.printLogo();

        db.setPath("posts");

        String name = UI.getName();

        int size = 0;
        int page = 1;



        //db.write(new Post("Abe Lincoln", "Four score and seven years ago our fathers brought forth on this continent a new nation.")); //43 chars line?
        ArrayList<Post> posts;

        //System.out.println(db.getPath() + "\n");

//        HashMap<Character, String> opts = new HashMap();
//        int page = 1;
//        opts.put('P', "View next page [");
//        opts.put('L', "View sleep log");
//        opts.put('P', "Make post");
//        UI.getOption(opts);

        while(true){

            UI.printLogo();
            posts = db.readPosts();
            size = posts.size();
            Collections.reverse(posts);
            try {
                for (int i = 3*(page-1); i < 3*(page-1) + 3; i++) System.out.println(posts.get(i) + "\n");

            } catch(IndexOutOfBoundsException e){}
            char ans  = UI.getPageOption(page, size, null);
            if(ans == 'M') {db.write(UI.makePost(name)); UI.finishPostNotif();}
            if(ans == '<' && page > 1) page --;
            if(ans == '>' && page < size/3 + size%3) page ++;
        }


    }
}