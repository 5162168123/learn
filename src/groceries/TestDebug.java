package groceries;

public class TestDebug {

        public static int binarySearch(int[] arr,int key) {
            if(arr.length==0){//数组为空时
                return -1;
            }
            int left = 0;
            int right =arr.length;
            while(left<=right){
                int mid = left+((right-left)>>1);//使用右移，因为移位比四则运算快
                if(arr[mid] == key){//相等返回
                    return mid;
                }else if(arr[mid]>key){//向左边查找
                    right = mid;
                }else if(arr[mid]<key){//向右边查找
                    left = mid+1;
                }
            }
            return -1;
        }
        public static void main(String[] args){
            int[] arr = {1,2,3,4,5,6,7,8};
            System.out.println(binarySearch(arr,9));//7
        }


}
