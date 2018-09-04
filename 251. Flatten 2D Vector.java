/* Implement an iterator to flatten a 2d vector.

Example:
Input: 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
Output: [1,2,3,4,5,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,2,3,4,5,6].
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
*/

// method 1:
public class Vector2D implements Iterator<Integer> {
    private int y;
    private int x;
    private List<List<Integer>> vec2d;
    public Vector2D(List<List<Integer>> vec2d) {
        y = 0;
        x = 0;
        this.vec2d = vec2d;
    }

    @Override
    public Integer next() {
        return vec2d.get(y).get(x++);
    }

    @Override
    public boolean hasNext() {
        while (y < vec2d.size()) {
            if (x < vec2d.get(y).size()) {
                return true;
            } else {
                y++;
                x = 0;
            }
        }
        return false;
    }
}

// method 2:
public class Vector2D {
    private Iterator<List<Integer>> it;
    private Iterator<Integer> cur;
    
    public Vector2D(List<List<Integer>> vec2d) {
        it = vec2d.iterator();
    }

    public int next() {
        return cur.next();
    }

    public boolean hasNext() {
        while ((cur == null || !cur.hasNext()) && it.hasNext()) {
            cur = it.next().iterator();
        }
        return cur != null && cur.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */