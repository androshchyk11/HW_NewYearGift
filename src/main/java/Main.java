import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    private static FileWriter fileWriter;
    private static FileReader fileReader;
    private static FileReader fileReaderInfo;
    private static Scanner scannerFromInput;
    private static Scanner scannerInfoFromFile;
    private static int price;
    private static int weight;
    private static String type;
    private static String name;

    private static int priceForFilter;
    private static int weightForFilter;
    private static String nameForFilter;
    private static String typeForFilter;
    private static int messageValue = 0;

    private static ListOfCandies candies = new ListOfCandies();

    private static Stream<Candy> giftStream;

    public static void main(String[] args) throws IOException {
        allProgram();
    }

    private static void allProgram() throws IOException {
        Scanner scanner = new Scanner(System.in);
        setTheStreams();

        getAllCandies();

        listToStream();

        consoleMessages(messageValue);

        int k;
        while (true) {
            k = scanner.nextInt();
            if (k == 1) {
                messageValue = 1;
                consoleMessages(messageValue);
                priceForFilter = scanner.nextInt();
                filterStreamByPrice();
                break;
            } else if (k == 2) {
                messageValue = 2;
                consoleMessages(messageValue);
                weightForFilter = scanner.nextInt();
                filterStreamByWeight();
                break;
            } else if (k == 3) {
                messageValue = 3;
                consoleMessages(messageValue);
                typeForFilter = scanner.next();
                filterStreamByType();
                break;
            } else if (k == 4) {
                messageValue = 4;
                consoleMessages(messageValue);
                nameForFilter = scanner.next();
                filterStreamByName();
                break;
            } else {
                messageValue = 5;
                consoleMessages(messageValue);
            }
        }
        fileReaderInfo.close();
        fileReader.close();
        fileWriter.close();
    }

    private static void consoleMessages(int messageValue) {
        if (messageValue == 0) {
            System.out.println("If you want to filter candies by price - press 1");
            System.out.println("If you want to filter candies by weight - press 2");
            System.out.println("If you want to filter candies by type - press 3");
            System.out.println("If you want to filter candies by name - press 4");
        } else if (messageValue == 1) {
            System.out.print("Input minimal price of a candy: ");
        } else if (messageValue == 2) {
            System.out.print("Input minimal weight of a candy: ");
        } else if (messageValue == 3) {
            System.out.print("Input type of a candy: ");
        } else if (messageValue == 4) {
            System.out.print("Input name of a candy: ");
        } else if (messageValue == 5) {
            System.out.println("Input value in range [1, 4]!");
        }
    }

    private static void filterStreamByName() {

        giftStream.filter(o -> o.getName().equals(nameForFilter))
                .forEach(o -> {
                    try {
                        fileWriter.write(o.getPrice() + " " + o.getWeight() + " " + o.getType() + " " + o.getName() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void filterStreamByType() {
        giftStream.filter(o -> o.getType().equals(typeForFilter))
                .forEach(o -> {
                    try {
                        fileWriter.write(o.getPrice() + " " + o.getWeight() + " " + o.getType() + " " + o.getName() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void filterStreamByWeight() {
        giftStream.filter(o -> o.getWeight() > weightForFilter)
                .forEach(o -> {
                    try {
                        fileWriter.write(o.getPrice() + " " + o.getWeight() + " " + o.getType() + " " + o.getName() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void filterStreamByPrice() {
        log.info("We entered filterStreamByPriceMethod! ");
        giftStream.filter(o -> o.getPrice() > priceForFilter)
                .forEach(o -> {
                    try {
                        fileWriter.write(o.getPrice() + " " + o.getWeight() + " " + o.getType() + " " + o.getName() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void getAllCandies() {

        getItems();
        ChocolateCandy roshen = new ChocolateCandy(price, weight, type, name);
        candies.addItem(roshen);

        getItems();
        ChocolateCandy svitoch = new ChocolateCandy(price, weight, type, name);
        candies.addItem(svitoch);

        getItems();
        ChocolateCandy vatsak = new ChocolateCandy(price, weight, type, name);
        candies.addItem(vatsak);

        getItems();
        ChewingCandy orbit = new ChewingCandy(price, weight, type, name);
        candies.addItem(orbit);

        getItems();
        ChewingCandy dirol = new ChewingCandy(price, weight, type, name);
        candies.addItem(dirol);

        getItems();
        ChewingCandy bubbleGum = new ChewingCandy(price, weight, type, name);
        candies.addItem(bubbleGum);
    }

    private static void listToStream() {
        giftStream = candies.listOfCandies.stream();
    }

    private static void infoStreamsSet() {
        try {
            fileReaderInfo = new FileReader("D:\\Epam_Homeworks\\HW_NewYearGift\\src\\main\\java\\Info.txt");
            scannerInfoFromFile = new Scanner(fileReaderInfo);
            log.info("InfoStream is found!");
        } catch (FileNotFoundException e) {
            log.error("Info file is not found!");
            e.printStackTrace();
        }
    }

    private static void setTheStreams() {
        infoStreamsSet();

        int i = 0;
        while (scannerInfoFromFile.hasNextLine()) {
            i += 1;
            if (i == 1) {
                String readerPath = scannerInfoFromFile.nextLine();
                try {
                    fileReader = new FileReader(readerPath);
                    log.info("Input file is found!");
                } catch (FileNotFoundException e) {
                    log.error("Input file is found!");
                    e.printStackTrace();
                }
                scannerFromInput = new Scanner(fileReader);
            } else if (i == 2) {
                String writerPath = scannerInfoFromFile.nextLine();
                log.info("Output file is found!");
                try {
                    fileWriter = new FileWriter(writerPath);
                } catch (IOException e) {
                    log.error("Output file is not found!");
                    e.printStackTrace();
                }
            }
        }
    }

    private static void getItems() {
        price = scannerFromInput.nextInt();
        weight = scannerFromInput.nextInt();
        type = scannerFromInput.next();
        name = scannerFromInput.next();
    }
}
