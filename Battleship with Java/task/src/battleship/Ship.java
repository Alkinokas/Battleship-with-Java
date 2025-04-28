package battleship;

public class Ship {

    private final String start;
    private final String end;

    public Ship (String start, String end) {
        this.start = start;
        this.end = end;
    }

    public boolean check(Battlefield battlefield) {
        String start = this.start;
        String end = this.end;

        // Set smaller number as start and bigger as end
        if (start.charAt(0) > end.charAt(0)) {
            String tmp = start;
            start = end;
            end = tmp;
        }

        if (Integer.parseInt(start.substring(1)) > Integer.parseInt(end.substring(1))) {
            String tmp = start;
            start = end;
            end = tmp;
        }

        // Checks if input isn't out of bounds ( > J)
        if ((start.charAt(0) - 'A' > 9) || (end.charAt(0) - 'A' > 9)) {
            System.out.println("Error! Out of Grid! Try again:");
            return true;
        }

        // checks if correct input is given (ship is in the same row or column)
        if (start.charAt(0) != end.charAt(0) && start.charAt(1) != end.charAt(1)) {
            System.out.println("Error! Wrong input! Try again:");
            return true;
        } else if (Integer.parseInt(start.substring(1)) < 1 || Integer.parseInt(end.substring(1)) < 1 ||
                Integer.parseInt(start.substring(1)) > 10 || Integer.parseInt(end.substring(1)) > 10) {
            System.out.println("Error! Wrong input! Try again:");
            return true;
        }

        if (start.charAt(0) == end.charAt(0)) {
            char rowChar = start.charAt(0);

            int length = getLength();

            boolean isHorizontal = true;

            if (battlefield.checkGrid(start, length, isHorizontal)) {
                battlefield.updateGrid(start, length, isHorizontal);
                return false;
            } else {
                return true;
            }

        } else {
            int colInt = start.charAt(1) - '0';

            int length = getLength();

            boolean isHorizontal = false;

            if (battlefield.checkGrid(start, length, isHorizontal)) {
                battlefield.updateGrid(start, length, isHorizontal);
                return false;
            } else {
                return true;
            }
        }
    }

    public int getLength() {
        if (start.charAt(0) == end.charAt(0)) {
            int colNumber1 = Integer.parseInt(String.valueOf(start.substring(1)));
            int colNumber2 = Integer.parseInt(String.valueOf(end.substring(1)));

            int biggerNumber = (colNumber1 > colNumber2) ? colNumber1 : colNumber2;
            int smallerNumber = (colNumber1 < colNumber2) ? colNumber1 : colNumber2;

            int length = biggerNumber - smallerNumber + 1;

            return length;

        } else {
            int rowChar1 = start.charAt(0) - 'A';
            int rowChar2 = end.charAt(0) - 'A';

            int biggerNumber = (rowChar1 > rowChar2) ? rowChar1 : rowChar2;
            int smallerNumber = (rowChar1 < rowChar2) ? rowChar1 : rowChar2;

            int length = biggerNumber - smallerNumber + 1;

            return length;
        }
    }
}
