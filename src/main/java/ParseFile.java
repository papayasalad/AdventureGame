
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseFile {
    public static PageTree parseFile(String filePath) throws FileNotFoundException {
        PageTree tree = new PageTree();
        int page = 0;

        Scanner scan = new Scanner(new File(filePath));
        while (scan.hasNextLine()) {
            page++;
            if (page == 1) {
                tree.setRoot(new PageNode(page));
            }

            String line = scan.nextLine();
            String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            String text = data[data.length - 1];
            boolean isEnding = data[0].equals("ENDING")? true : false;

            PageNode node = tree.getNode(page);
            node.setText(text);
            if (isEnding) {
                node.setEnding(true);
            } else {
                for (int i = 0; i < data.length - 1; i++) {
                    PageNode prospectiveChild = tree.getNode(Integer.parseInt(data[i]));
                    if (prospectiveChild != null) {
                        node.addChild(prospectiveChild);
                    } else {
                        node.addChild(new PageNode(Integer.parseInt(data[i])));
                    }
                }
            }
        }
        return tree;
    }
}
