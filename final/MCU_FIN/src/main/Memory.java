package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

// 데이터 저장 공간의 집합
public class Memory {
    private Vector<String> memory;

    public Vector<String> getMemory() {
        return memory;
    }

    public Scanner scanner;
    public int stackloca;
    private int[] dataSegment;
    private int[] stackSegment;
    private int[] heapSegment;

    private CPU.Register mar;
    private CPU.Register mbr;

    public Memory() {
        this.memory = new Vector<String>();
        this.dataSegment = new int[1000];
        this.stackSegment = new int[1000];
        this.heapSegment = new int[1000];
        this.stackloca = 0;
    }

    public void setFile(File file) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parse() {
        while (scanner.hasNext()) {
            String token = scanner.nextLine();
            if (!token.isEmpty()) {
                this.memory.add(token);
            }
        }
        scanner.close();
    }

    public void associate(CPU.Register mar, CPU.Register mbr) {
        this.mar = mar;
        this.mbr = mbr;
    }

    // data segment code
    public void loadData() {
        int address = mar.getValue();
        mbr.setValue(dataSegment[address]);
    }

    public void storeData() {
        int address = mar.getValue();
        int value = mbr.getValue();
        dataSegment[address] = value;
    }

    // heap segment code
    public void loadHeap() {
        int address = mar.getValue();
        mbr.setValue(heapSegment[address]);
    }

    public void storeHeap() {
        int address = mar.getValue();
        int value = mbr.getValue();
        heapSegment[address] = value;
    }

    // stack segment
    public void loadStack() {
        int address = mar.getValue();
        mbr.setValue(stackSegment[stackloca * 20 + address]);
    }

    public void storeStack() {
        int address = mar.getValue();
        int value = mbr.getValue();
        stackSegment[stackloca * 20 + address] = value;
    }

    public void spop() {
        // 안에 있는 값을 다 초기화 시켜주기
        for(int i = 0; i < 20; i++){
            stackSegment[stackloca * 20 + i] = 0;
        }
        this.stackloca--;
    }

    public void spush() {
        this.stackloca++;
    }

    public String showDS() {
        StringBuilder sb = new StringBuilder();
        sb.append("[data segment] ");
        for (int i = 0; i < dataSegment.length; i++) {
            sb.append("indxex: " + i + " -> size: " + dataSegment[i]).append(", ");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}
