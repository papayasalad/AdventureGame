import java.util.ArrayList;
import java.util.List;

public class PageTree {
    private PageNode root;

    public PageNode getRoot() {
        return this.root;
    }

    public void setRoot(PageNode root) {
        this.root = root;
    }

    public PageNode getNode(int pageNumber) {
        return getNode(root, pageNumber);
    }

    private PageNode getNode(PageNode root, int pageNumber) {
        if (root != null) {
            if (root.getPageNumber() == pageNumber) {
                return root;
            } else {
                for (PageNode child : root.getChildren()) {
                    PageNode result = getNode(child, pageNumber);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }

    public List<PageNode> cheat(int desiredEnding) {
        List<PageNode> path = new ArrayList<>();
        path.add(root);
        cheat(path, desiredEnding);
        return path;
    }

    private boolean cheat(List<PageNode> path, int desireEnding) {
        PageNode current = path.get(path.size() - 1);
        if (current.getPageNumber() == desireEnding) {
            return true;
        }

        else if (!current.isEnding()) {
            for (PageNode child : current.getChildren()) {
                path.add(child);
                if(cheat(path, desireEnding)){
                    return true;
                }
                path.remove(child);
            }
        }
        return false;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(PageNode root) {
        if (root != null) {
            System.out.println("\npage is " + root.getPageNumber());
            System.out.println("text is " + root.getText());
            System.out.printf("is %s ending\n", root.isEnding()? "" : "not");
            System.out.println(root.getChildren().size() + " children");
            for (PageNode child : root.getChildren()) {
                System.out.println(child.getPageNumber() + "(" + child.isEnding() + ")" + "(" + child.getText() + ")");
            }

            for (PageNode child : root.getChildren()) {
                printTree(child);
            }
        }
    }

}