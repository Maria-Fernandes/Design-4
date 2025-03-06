
// Time Complexity : O(1) hasNext O(n) next
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
/*
iterator
 */

// "static void main" must be defined in a public class.


class SkipIterator implements Iterator<Integer> {
    Integer nextEl;
    HashMap<Integer, Integer> skipMap;
    Iterator<Integer> it;
    public SkipIterator(Iterator<Integer> it){
        this.it = it;
        this.skipMap = new HashMap<>();
        advance();
    }

    private void advance(){
        this.nextEl = null;
        while(nextEl == null && it.hasNext()){
            Integer currEl = it.next();
            if(!skipMap.containsKey(currEl)){
                nextEl = currEl;
            } else {
                skipMap.put(currEl, skipMap.get(currEl) - 1);
                skipMap.remove(currEl, 0);
            }
        }
    }

    public void skip(int num) {  //O(n)
        if(num == nextEl){
            advance();
        } else {
            skipMap.put(num, skipMap.getOrDefault(num, 0) + 1);
        }
    }
    @Override
    public boolean hasNext() { //O(1)
        return nextEl != null;
    }

    @Override
    public Integer next() { //O(n)
        int temp = nextEl;
        advance();
        return temp;
    }


}

public class Main {

    public static void main(String[] args) {

        SkipIterator sit = new SkipIterator(Arrays.asList(5,6,7,5,6,8,9,5,5,6,8).iterator());

        System.out.println(sit.hasNext());// true
        System.out.println(sit.next()); //5   nextEl = 6
        sit.skip(5);  // will be store in map
        System.out.println(sit.next());// 6 nextEl = 7
        System.out.println(sit.next()); // 7 nextEl = 6
        sit.skip(7); // nextEl = 6
        sit.skip(9); // store in map

        System.out.println(sit.next()); // 6 nextEl = 8

        System.out.println(sit.next()); //8
        System.out.println(sit.next());// 5
        sit.skip(8); //nextEl = null
        sit.skip(5);
        System.out.println(sit.hasNext()); //true
        System.out.println(sit.next()); //6
        System.out.println(sit.hasNext()); //false

    }

}
