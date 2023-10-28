package Dijkstra;

public class PrioQueue {

        int ptr;
        Path[] arr;

        public PrioQueue(int height) {
            ptr = 0;
            arr = new Path[(int) Math.pow(2, height) - 1];
        }

        public void add(Path path) {
            arr[ptr] = path;
            update(ptr);
            ptr++;
        }

        public void update(int curr){
            int num;

            if (ptr % 2 == 1) {
                num = 1;
            } else {
                num = 2;
            }

            for (int i = (ptr - num) / 2; i >= 0; i = (i - num) / 2) {
                if (arr[curr].time >= arr[i].time) {
                    break;
                }
                Path temp = arr[curr];
                arr[curr] = arr[i];
                arr[i] = temp;


                curr = i;
            }

        }

        public Path remove() {
            if(ptr ==0){
                return null;
            }

            Path val = arr[0];
            ptr--;
            arr[0] = arr[ptr];
            arr[ptr] = null;

            int i = 0;
            int left = 1;
            int right = 2;

            while ((left < ptr) && (right < ptr)) {
                if (arr[left].time < arr[i].time || arr[right].time < arr[i].time) {
                    if (arr[left].time < arr[right].time) {
                        Path temp = arr[left];
                        arr[left] = arr[i];
                        arr[i] = temp;

                        i = i * 2 + 1;
                    } else {
                        Path temp = arr[right];
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
