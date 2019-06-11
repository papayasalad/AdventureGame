import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PageNode implements Comparable<PageNode>{
    private int pageNumber;
    private String text;
    private boolean isEnding;
    private Set<PageNode> children = new TreeSet<>();

    public PageNode(int pageNumber) {
        this(pageNumber, "", false);
    }

    public PageNode(int pageNumber, String text, boolean isEnding) {
        this.pageNumber = pageNumber;
        this.text = text;
        this.isEnding = isEnding;
    }

    public Set<PageNode> getChildren() {
        return children;
    }

//    public void addChildren(String[] pageList) {
//         if (!isEnding) {
////             System.out.print("children: "); // to be deleted
//             for (int i = 0; i < pageList.length - 1; i++) {
//
//                 addChild(new PageNode(Integer.parseInt(pageList[i])));
////                 System.out.print(pageList[i] + " "); // to be deleted
//             }
//         }
//    }

    public void addChild(PageNode node) {
        children.add(node);
    }

    public PageNode getChild(int pageNumber) {
        for (PageNode child : children) {
            if (child.pageNumber == pageNumber) {
                return child;
            }
        }
        return null;
    }

    public boolean isEnding() {
        return isEnding;
    }

    public void setEnding(boolean isEnding) {
        this.isEnding = isEnding;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int compareTo(PageNode o) {
        return this.pageNumber - o.getPageNumber();
    }
}
