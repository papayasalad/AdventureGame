import java.util.Set;
import java.util.TreeSet;

public class PageNode {
    private int pageNumber;
    private String text;
    private boolean isEnding;
    private Set<PageNode> children = new TreeSet<>();

}
