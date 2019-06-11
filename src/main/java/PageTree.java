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
        return cheat(new ArrayList<>(), root, desiredEnding);
    }

    private List<PageNode> cheat(List<PageNode> list, PageNode root, int desireEnding) {
        list.add(root);
        if (root != null) {
            if (root.getPageNumber() == desireEnding) {
                return list;
            } else {
                for (PageNode child : root.getChildren()) {
                    List<PageNode> result = cheat(list, child, desireEnding);
                    if (result.get(result.size() - 1) != null) {
                        return result;
                    }
                }

            }
        }
        return list;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(PageNode root) {
        if (root == null) {
            return;
        } else {
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