public class PrioArray {

    int ptr;
    Integer[] arr;

    public PrioArray(int height) {
        ptr = 0;
        arr = new Integer[(int) Math.pow(2, height) - 1];
    }

    public void add(int val) {
        arr[ptr] = val;
        int curr = ptr;
        int num;

        if (ptr % 2 == 1) {
            num = 1;
        } else {
            num = 2;
        }

        for (int i = (ptr - num) / 2; i >= 0; i = (i - num) / 2) {
            if (arr[curr] >= arr[i]) {
                break;
            }
            int temp = arr[curr];
            arr[curr] = arr[i];
            arr[i] = temp;
            curr = i;
        }

        ptr++;
    }

    public Integer remove() {
        if(ptr ==0){
            return null;
        }

        int val = arr[0];
        ptr--;
        arr[0] = arr[ptr];
        arr[ptr] = null;

        int i = 0;
        int left = 1;
        int right = 2;

        while ((left < ptr) && (right < ptr)) {
            if (arr[left] < arr[i] || arr[right] < arr[i]) {
                if (arr[left] < arr[right]) {
                    int temp = arr[left];
                    arr[left] = arr[i];
                    arr[i] = temp;

                    i = i * 2 + 1;
                } else {
                    int temp = arr[right];
                    arr[right] = arr[i];
                    arr[i] = temp;

                    i = i * 2 + 2;
                }
            } else {
                break;
            }
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
        return val;
    }
}

