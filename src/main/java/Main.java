import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern pattern = Pattern.compile("(\\w+) (\\d{2}):(\\d{2}) (\\d{2}):(\\d{2})");

    public static void main(String[] args) throws IOException {

        //String url = System.getProperty("user.dir") + "/inpute1.txt";
        String url = args[0];
        Scanner dataFromFile = new Scanner(Paths.get(url), "UTF-8");
        PrintWriter printWriter = new PrintWriter(System.getProperty("user.dir") + "/output.txt", "UTF-8");

        List<Item> items = new ArrayList<>();
        List<Item> itemsPosh = new ArrayList<>();
        List<Item> itemsGrotty = new ArrayList<>();

        while (dataFromFile.hasNext()) {
            String line = dataFromFile.nextLine().trim();
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                int sH = Integer.parseInt(m.group(2));
                int sM = Integer.parseInt(m.group(3));
                int eH = Integer.parseInt(m.group(4));
                int eM = Integer.parseInt(m.group(5));
                CompanyName cN;
                if (m.group(1).equals(CompanyName.POSH.toString())) {
                    cN = CompanyName.POSH;
                } else {
                    cN = CompanyName.GROTTY;
                }
                items.add(new Item(cN, sH, sM, eH, eM));
            } else {
                System.out.println("Incorrect data line.");
            }
        }

        Collections.sort(items);

        int i = 0;
        Item it = items.get(0);
        while (i < (items.size() - 1)) {
            i++;
            Item item = items.get(i);
            if ((item.getTimeEnd() > it.getTimeEnd()) && (item.getTimeStart() > it.getTimeStart())) {
                if (it.getCompanyName().equals(CompanyName.POSH)) {
                    itemsPosh.add(it);
                } else {
                    itemsGrotty.add(it);
                }
                it = item;
            } else if (item.getTimeEnd() == it.getTimeEnd()) {
                if (item.getTimeStart() == it.getTimeStart()) {
                    if (it.getCompanyName().equals(CompanyName.POSH)) {
                        //itemsPosh.add(it);
                    } else {
                        //itemsGrotty.add(it);
                    }
                } else {

                }
            }
            if (i == items.size() - 1) {
                if (it.getCompanyName().equals(CompanyName.POSH)) {
                    itemsPosh.add(it);
                } else {
                    itemsGrotty.add(it);
                }
            }
        }
        for (Item elem : itemsPosh) {
            printWriter.write(String.format("%s %02d:%02d %02d:%02d%n", elem.getCompanyName(),
                    elem.getStartHour(), elem.getStartMinutes(), elem.getEndHour(), elem.getEndMinutes()));
/*            System.out.printf("%s %02d:%02d %02d:%02d%n", elem.getCompanyName(),
                    elem.getStartHour(), elem.getStartMinutes(), elem.getEndHour(), elem.getEndMinutes());*/
        }
        printWriter.println();

        for (Item elem : itemsGrotty) {
            printWriter.write(String.format("%s %02d:%02d %02d:%02d%n", elem.getCompanyName(),
                    elem.getStartHour(), elem.getStartMinutes(), elem.getEndHour(), elem.getEndMinutes()));
/*            System.out.printf("%s %02d:%02d %02d:%02d%n", elem.getCompanyName(),
                    elem.getStartHour(), elem.getStartMinutes(), elem.getEndHour(), elem.getEndMinutes());*/
        }
        printWriter.close();
    }
}
