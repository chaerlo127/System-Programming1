package main;
import constant.Constant;
import instruction_design.SOperator;

import java.util.Scanner;

public class CPU {
    /**
     * Enumeration
     */
    private enum EState {
        eStopped, eRunning
    }

    private enum ESegment{
        NONE, DATA, HEAP, STACK;
    }
    private Scanner scanner;
    private Memory memory;
    private EState eState;

    private ESegment segment;

    public void associate(Memory memory, Scanner scanner) {
        this.memory = memory;
        this.scanner = scanner;
    }

    /**
     * register
     */
    public Register mbr;
    public Register mar, hs, ds, ss, cs, pc, ac1, ac2; // code segment register, program counter
    private Instruction instruction;


    private boolean bGratherThan;
    private boolean bEqual;

    /**
     * constructor
     */
    public CPU() {
        mar = new Register();
        mbr = new Register();
        cs = new Register();
        hs = new Register();
        ds = new Register();
        ss = new Register();
        pc = new Register();
        ac1 = new Register();
        ac2 = new Register();

        this.bEqual = false;
        this.bGratherThan = false;
        segment = ESegment.NONE;
    }

    public void initialize() {
        mar.setValue(0);
        mbr.setValue(0);
        cs.setValue(0);
        pc.setValue(0);
        ac1.setValue(0);
        ac2.setValue(0);

        this.bEqual = false;
        this.bGratherThan = false;
        segment = ESegment.NONE;
    }


    public String start(String processName) {
        System.out.println(this.pc.value);
        this.eState = EState.eRunning;
        return this.run(processName); // 원래는 thread 의 역할임.
    }

    // 무한 루핑 (polling)
    private String run(String processName) {
        System.out.println("[This Running Process Name]: " + processName);

        StringBuilder sb = new StringBuilder();

        while (this.eState == EState.eRunning) {
            this.fetch(sb);
            this.decode();
            this.execute(sb);
        }
        memory.initialize();
        initialize();
        return sb.toString();
    }

    /**
     * cpu instruction cycle method
     */

    public void fetch(StringBuilder sb) {
        instruction = new Instruction(this.memory.getMemory().get(cs.getValue() + pc.getValue()));
        System.out.println(this.pc.value + " : " + instruction.getOperator());
        sb.append(this.pc.value + " : " + instruction.getOperator()).append("<br>");

    }

    public void decode() {
    }

    public void execute(StringBuilder sb) {
        switch(instruction.getOperator()) {
            case LOADA: loada(); break;
            case LOADC: loadc(); break;
            case JMP: jump(); break;
            case GTJ: gtj(); break;
            case GEJ: gej(); break;
            case AND: break;
            case NOT: break;
            case HALT: haltProgram(); break;
            case ADDA: segment = ESegment.DATA; adda(); break;
            case ADDC: segment = ESegment.DATA; addc(); break;
            case SUBA: segment = ESegment.DATA; suba(); break;
            case SUBC: segment = ESegment.DATA; subc(); break;
            case MULA: segment = ESegment.DATA; mula(); break;
            case MULC: segment = ESegment.DATA; mulc(); break;
            case DIVA: segment = ESegment.DATA; diva(); break;
            case DIVC: segment = ESegment.DATA; divc(); break;
            case HPUSH: hpush(); break;
            case HPOP: hpop(); break;
            case HLOADA: hloada(); break;
            case HLOADC: loadc(); break;
            case HADDA: segment = ESegment.HEAP; adda(); break;
            case HADDC: segment = ESegment.HEAP; addc(); break;
            case HSUBA: segment = ESegment.HEAP; suba(); break;
            case HSUBC: segment = ESegment.HEAP; subc(); break;
            case HMULA: segment = ESegment.HEAP; mula(); break;
            case HMULC: segment = ESegment.HEAP; mulc(); break;
            case HDIVA: segment = ESegment.HEAP; diva(); break;
            case HDIVC: segment = ESegment.HEAP; divc(); break;
            case HJMP: break;
            case SPUSH: spush(); break;
            case SPOP: spop(); break;
            case SLOADA: sloada(); break;
            case SLOADC: loadc(); break;
            case SADDA: segment = ESegment.STACK; adda(); break;
            case SADDC: segment = ESegment.STACK; addc(); break;
            case SSUBA: segment = ESegment.STACK; suba(); break;
            case SSUBC: segment = ESegment.STACK; subc(); break;
            case SMULA: segment = ESegment.STACK; mula(); break;
            case SMULC: segment = ESegment.STACK; mulc(); break;
            case SDIVA: segment = ESegment.STACK; diva(); break;
            case SDIVC: segment = ESegment.STACK; divc(); break;
            case SJMP: sjump(); break;
            case LOADM: break;
            case STOREA: storea(); break;
            case HSTOREA: hstorea(); break;
            case SSTOREA: sstorea(); break;

        }
        pc.setValue(pc.getValue() + 1);
    }


    /**
     * operator method
     * */

    private void haltProgram() {
        this.eState = EState.eStopped;
        System.out.println("[System the end]");
        memory.addPage(); // 페이지 위치 조정 (다음 프로그램 실행을 위해)
    }

    private void loada() {
        if (instruction.getOperand() == Constant.CPU.keyboard) {
            System.out.println("[학생 수를 입력하세요!]");
            mbr.setValue(scanner.nextInt());
        } else if (instruction.getOperand() == Constant.CPU.random) {
            int score = (int) (Math.random() * 51) + 50;
            System.out.println("[[[[[[[[[[[[학생들의 랜덤 점수]]]]]]]]]]]]: " + score);
            mbr.setValue(score);
        } else {
            mar.setValue(instruction.getOperand());
            memory.loadData();
        }
    }

    private void loadc() {
        mbr.setValue(instruction.getOperand());
    }

    private void storea() {
        if (instruction.getOperand() == Constant.CPU.monitor) {
            System.out.println("[[[[[[[[[결과 값]]]]]]]]]: " + mbr.getValue());
        } else {
            // mar에 주소를 저장해두고
            mar.setValue(instruction.getOperand());
            // mbr에 저장되어 있는 것을 찾아서 data segment 에 저장!
            memory.storeData();
        }
    }

    private void adda() {
        ac1.setValue(mbr.getValue());
        // mar에 주소를 저장해두고,mbr로 불러오기
        mar.setValue(instruction.getOperand());
        if(segment.equals(ESegment.DATA)) memory.loadData();
        else if(segment.equals(ESegment.HEAP)) memory.loadHeap();
        else if (segment.equals(ESegment.STACK)) memory.loadStack();
        segment = ESegment.NONE;
        ac2.setValue(mbr.getValue());
        // ac1, ac2를 이용해서 값을 변경
        ac1.setValue(ac1.getValue() + ac2.getValue());
        mbr.setValue(ac1.getValue());

    }

    private void addc() {
        // mbr 에 저장된 값을 ac1 에 이동
        ac1.setValue(mbr.getValue());
        // instruction 에 저장되어 있는 상수 값을 ac2 로 이동
        ac2.setValue(instruction.getOperand());
        ac1.setValue(ac1.getValue() + ac2.getValue());
        mbr.setValue(ac1.getValue());
    }

    private void suba() {
        ac1.setValue(mbr.getValue());
        // mar에 주소를 저장해두고,mbr로 불러오기
        mar.setValue(instruction.getOperand());
        if(segment.equals(ESegment.DATA)) memory.loadData();
        else if(segment.equals(ESegment.HEAP)) memory.loadHeap();
        else if (segment.equals(ESegment.STACK)) memory.loadStack();
        segment = ESegment.NONE;
        ac2.setValue(mbr.getValue());

        if (ac1.getValue() == ac2.getValue()) this.bEqual = true;
        if (ac1.getValue() > ac2.getValue()) this.bGratherThan = true;

        // ac1, ac2를 이용해서 값을 변경
        ac1.setValue(ac1.getValue() - ac2.getValue());
        mbr.setValue(ac1.getValue());
    }

    private void subc() {
        // mbr 에 저장된 값을 ac1 에 이동
        ac1.setValue(mbr.getValue());
        // instruction 에 저장되어 있는 상수 값을 ac2 로 이동
        ac2.setValue(instruction.getOperand());

        if (ac1.getValue() == ac2.getValue()) this.bEqual = true;
        if (ac1.getValue() > ac2.getValue()) this.bGratherThan = true;

        ac1.setValue(ac1.getValue() - ac2.getValue());
        mbr.setValue(ac1.getValue());
    }

    private void mula() {
        ac1.setValue(mbr.getValue());
        // mar에 주소를 저장해두고,mbr로 불러오기
        mar.setValue(instruction.getOperand());
        if(segment.equals(ESegment.DATA)) memory.loadData();
        else if(segment.equals(ESegment.HEAP)) memory.loadHeap();
        else if (segment.equals(ESegment.STACK)) memory.loadStack();
        segment = ESegment.NONE;
        ac2.setValue(mbr.getValue());
        // ac1, ac2를 이용해서 값을 변경
        ac1.setValue(ac1.getValue() * ac2.getValue());
        mbr.setValue(ac1.getValue());
    }

    private void mulc() {
        // mbr 에 저장된 값을 ac1 에 이동
        ac1.setValue(mbr.getValue());
        // instruction 에 저장되어 있는 상수 값을 ac2 로 이동
        ac2.setValue(instruction.getOperand());
        ac1.setValue(ac1.getValue() * ac2.getValue());
        mbr.setValue(ac1.getValue());
    }

    private void diva() {
        ac1.setValue(mbr.getValue());
        // mar에 주소를 저장해두고,mbr로 불러오기
        mar.setValue(instruction.getOperand());
        if(segment.equals(ESegment.DATA)) memory.loadData();
        else if(segment.equals(ESegment.HEAP)) memory.loadHeap();
        else if (segment.equals(ESegment.STACK)) memory.loadStack();
        segment = ESegment.NONE;
        ac2.setValue(mbr.getValue());
        // ac1, ac2를 이용해서 값을 변경
        ac1.setValue(ac1.getValue() / ac2.getValue());
        mbr.setValue(ac1.getValue());
    }

    private void divc() {
        // todo : 여기 봐야함
        System.out.println("ac1 " + mbr.getValue());
        System.out.println("ac2 " + instruction.getOperand());
        // mbr 에 저장된 값을 ac1 에 이동
        ac1.setValue(mbr.getValue());
        // instruction 에 저장되어 있는 상수 값을 ac2 로 이동
        ac2.setValue(instruction.getOperand());
        ac1.setValue(ac1.getValue() / ac2.getValue());
        mbr.setValue(ac1.getValue());
    }

    private void gtj() {
        if (this.bGratherThan) {
            this.pc.setValue(instruction.getOperand()-1);
        }
        this.bGratherThan = false;
    }

    private void gej() {
        if (this.bEqual || this.bGratherThan) {
            this.pc.setValue(instruction.getOperand()-1);
        }
        this.bGratherThan = false;
        this.bEqual = false;
    }

    private void jump() {
        System.out.println("----------------------jump---------------------");
        this.pc.setValue(instruction.getOperand());
        this.bEqual = false;
        this.bGratherThan = false;
    }

    // heap segment operator
    private void hpush() {
    }

    private void hpop() {
    }
    private void hloada() {
        mar.setValue(instruction.getOperand());
        memory.loadHeap();
    }

    private void hstorea() {
        // mar에 주소를 저장해두고
        mar.setValue(instruction.getOperand());
        // mbr에 저장되어 있는 것을 찾아서 data segment 에 저장!
        memory.storeHeap();
    }

    // stack segment operator
    private void sloada() {
        mar.setValue(instruction.getOperand());
        memory.loadStack();
    }
    private void sstorea() {
        mar.setValue(instruction.getOperand());
        memory.storeStack();
    }
    private void spop() {
        mar.setValue(instruction.getOperand());
        memory.spop();
    }

    private void spush() {
        memory.spush();
    }

    private void sjump() {
        if(instruction.getOperand() == 4) {
            mar.setValue(4);
            System.out.println("----------------------method finish----------------------");
            memory.loadStack();
            this.pc.setValue(mbr.getValue());
        }
        else {
            System.out.println("----------------------method jump----------------------");
            mar.setValue(4);
            mbr.setValue(this.pc.getValue());
            memory.storeStack();
            this.pc.setValue(instruction.getOperand()-1);
        }

        this.bEqual = false;
        this.bGratherThan = false;
    }

    /**
     * inner class
     *
     */
    public class Register{
        protected int value;

        public Register() {

        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    public class IR extends Register{

    }

    public int changeStringToInt(String command) {
        command = command.replace("0x", "");
        return Integer.parseInt(command, 16);
    }


    private class Instruction {
        private int operator;
        private int operand;

        public Instruction(String line) {
            String[] tokens = line.split(" ");
            this.operator = changeStringToInt(tokens[0]);
            this.operand = Integer.MAX_VALUE;

            if (tokens.length > 1) {
                this.operand = changeStringToInt(tokens[1]);
            }
        }

        public SOperator getOperator() {
            for(SOperator operator: SOperator.values()) {
                if(operator.getCode() == this.operator) {
                    return operator;
                }
            }return null;
        }

        public int getOperand() {
            return this.operand;
        }

    }
}
