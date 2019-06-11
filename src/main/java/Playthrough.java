import java.util.*;

public class Playthrough {
    private LinkedList<PageNode> choices = new LinkedList<>();
    private PageTree book;

    public Playthrough(PageTree book) {
        this.book = book;
    }

    public void playGame() {
        playGame(0);
    }

    public void playGame(int pageNumber) {
        PageNode current;
        if (pageNumber == 0) {
            choices.clear();
            current = book.getRoot();
            choices.addFirst(current);
        } else {
            truncateChoices(pageNumber);
            current = choices.getLast();
        }

        do {
            System.out.println(current.getText());
            String options = "";
            for (PageNode child : current.getChildren()) {
                options += child.getPageNumber() + ", ";
            }
            System.out.println("Choose an option: " + options.substring(0, options.length() - 2));
            Scanner scan = new Scanner(System.in);
            int num = scan.nextInt();
            current = current.getChild(num);
            choices.add(current);
        } while (!current.isEnding());
        System.out.println(current.getText());
    }

    public void truncateChoices(int pageNumber) {
        while (choices.getLast().getPageNumber() != pageNumber) {
            choices.removeLast();
        }
    }

    public LinkedList<PageNode> getChoices() { return this.choices; }
}
