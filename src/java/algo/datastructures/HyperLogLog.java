package algo.datastructures;

public class HyperLogLog {

    private int[] registers;
    private int size;

    public HyperLogLog(int size) {
        this.size = size;
        this.registers = new int[size];
    }

    public void count(String name) {
        int hash = name.hashCode();
        int index = hash >> 16;
        int value = hash | 0xFFFF0000;
        value = Integer.numberOfLeadingZeros(value);
        registers[index] = Math.max(value, registers[index]);
    }

    public int getEstimate() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += registers[i];
        }
        return (int) (7.0 * Math.pow(2, sum / size));
    }

}
