package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }

    final String path = "TODO";

    record Shifts(int firstFrom, int firstTo, int secondFrom, int secondTo) {

        int isContained() {
            if (firstFrom <= secondFrom && firstTo >= secondTo) {
                return 1;
            }

            if (secondFrom <= firstFrom && secondTo >= firstTo) {
                return 1;
            }

            return 0;
        }

    }

    final List<Shifts> shiftsList = new ArrayList<>();

    void run() throws IOException {
        this.readShifts();
        this.printSum();
    }

    void printSum() {
        System.out.println(this.shiftsList.stream().map(Shifts::isContained).reduce(Integer::sum).get());
    }

    void readShifts() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path + "input.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                String[] first = parts[0].split("-");
                String[] second = parts[1].split("-");

                Shifts shifts = new Shifts(Integer.parseInt(first[0]), Integer.parseInt(first[1]), Integer.parseInt(second[0]), Integer.parseInt(second[1]));
                this.shiftsList.add(shifts);
            }
        }
    }
}