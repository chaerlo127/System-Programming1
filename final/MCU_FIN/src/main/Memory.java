package main;

import constant.Constant;

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
    public int[] segmentation;
    public int pageNumber;
    public int totalPageNumber;

    private CPU.Register mar;
    private CPU.Register mbr;

    public Memory() {
        this.memory = new Vector<String>();
        this.segmentation = new int[3000];
        this.stackloca = 0;
        this.pageNumber = 0;
        totalPageNumber = this.segmentation.length/600; // 전체 세그먼트의 갯수로 나눔.
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
        System.out.println("[Paging]-------------------------------------------");
        System.out.println("[현재 페이지 숫자] : " + this.pageNumber +  "[전체 페이지 숫자] : " + this.totalPageNumber);
    }

    public void associate(CPU.Register mar, CPU.Register mbr) {
        this.mar = mar;
        this.mbr = mbr;
    }

    // data segment code
    public void loadData() {
        int address = mar.getValue();
        // 물리적 주소 (페이지 넘버 + 데이터 세그먼트 주소) + 논리적 주소
        mbr.setValue(segmentation[Constant.Memory.totalSegmentSize * pageNumber + Constant.Memory.datasegement + address]);
        System.out.println("---------------------------------------- load [address]: " + address + " [value]: " + mbr.getValue());
    }

    public void storeData() {
        int address = mar.getValue();
        int value = mbr.getValue();
        System.out.println("---------------------------------------- [address]: " + address + " [value]: " + value);
        // 물리적 주소 (페이지 넘버 + 데이터 세그먼트 주소) + 논리적 주소
        segmentation[Constant.Memory.totalSegmentSize * pageNumber + Constant.Memory.datasegement + address] = value;
    }

    // heap segment code
    public void loadHeap() {
        int address = mar.getValue();
        // 물리적 주소 (페이지 넘버 + 데이터 세그먼트 주소) + 논리적 주소
        mbr.setValue(segmentation[Constant.Memory.totalSegmentSize * pageNumber + Constant.Memory.heapsegment + address]);
    }

    public void storeHeap() {
        int address = mar.getValue();
        int value = mbr.getValue();
        System.out.println("---------------------------------------- [address]: " + address + " [value]: " + value);
        // 물리적 주소 (페이지 넘버 + 데이터 세그먼트 주소) + 논리적 주소
        segmentation[Constant.Memory.totalSegmentSize * pageNumber + Constant.Memory.heapsegment + address] = value;
    }

    // stack segment
    public void loadStack() {
        int address = mar.getValue();
        // 물리적 주소 (페이지 넘버 + 데이터 세그먼트 주소) + 논리적 주소
        mbr.setValue(segmentation[Constant.Memory.totalSegmentSize * pageNumber + Constant.Memory.stacksegment + stackloca * 20 + address]);
    }

    public void storeStack() {
        int address = mar.getValue();
        int value = mbr.getValue();
        // 물리적 주소 (페이지 넘버 + 데이터 세그먼트 주소) + 논리적 주소
        segmentation[Constant.Memory.totalSegmentSize * pageNumber + Constant.Memory.stacksegment + stackloca * 20 + address] = value;
        System.out.println("---------------------------------------- store stack [address]: " + address + " [value]: " + value);

    }

    public void spop() {
        // 안에 있는 값을 다 초기화 시켜주기
        for(int i = 0; i < 20; i++){
            // 물리적 주소 (페이지 넘버 + 데이터 세그먼트 주소) + 논리적 주소
            segmentation[Constant.Memory.totalSegmentSize * pageNumber + Constant.Memory.stacksegment + stackloca * 20 + i] = 0;
        }
        this.stackloca--;
    }

    public void spush() {
        this.stackloca++;
    }

//    public String showDS() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("[data segment] ");
//        for (int i = 0; i < dataSegment.length; i++) {
//            sb.append("indxex: " + i + " -> size: " + dataSegment[i]).append(", ");
//        }
//        System.out.println(sb.toString());
//        return sb.toString();
//    }

}
