//package game;
//
//import javolution.context.AllocatorContext;
//import org.jscience.mathematics.structure.Field;
//import org.jscience.mathematics.vector.Vector;
//
//import java.util.ArrayList;
//
//public class DirectionVector extends Vector {
//
//    double x;
//    double y;
////    ArrayList v = new ArrayList<Double>();
//    ArrayList v = new ArrayList<Double>();
//
//    public DirectionVector(double x, double y){
//        this.x = x;
//        this.y = y;
//
//        this.v.add(x);
//        this.v.add(y);
//    };
//
//
//    /**
//     * Returns the number of elements  held by this vector.
//     *
//     * @return this vector dimension.
//     */
//    @Override
//    public int getDimension() {
//        return v.size();
//    }
//
//    /**
//     * Returns a single element from this vector.
//     *
//     * @param i the element index (range [0..n[).
//     * @return the element at <code>i</code>.
//     * @throws IndexOutOfBoundsException <code>(i < 0) || (i >= size())</code>
//     */
//    @Override
//    public Field get(int i) {
//        return v.get(i);
//    }
//
//    @Override
//    public Object plus(Object o) {
//        return null;
//    }
//
//    /**
//     * Returns the negation of this vector.
//     *
//     * @return <code>-this</code>.
//     */
//    @Override
//    public Vector opposite() {
//        return null;
//    }
//
//    /**
//     * Returns the sum of this vector with the one specified.
//     *
//     * @param that the vector to be added.
//     * @return <code>this + that</code>.
//     * @throws DimensionException is vectors dimensions are different.
//     */
//    @Override
//    public Vector plus(Vector that) {
//        return null;
//    }
//
//    /**
//     * Returns the product of this vector with the specified coefficient.
//     *
//     * @param k the coefficient multiplier.
//     * @return <code>this · k</code>
//     */
//    @Override
//    public Vector times(Field k) {
//        return null;
//    }
//
//    /**
//     * Returns the dot product of this vector with the one specified.
//     *
//     * @param that the vector multiplier.
//     * @return <code>this · that</code>
//     * @throws DimensionException if <code>this.dimension() != that.dimension()</code>
//     * @see <a href="http://en.wikipedia.org/wiki/Dot_product">
//     * Wikipedia: Dot Product</a>
//     */
//    @Override
//    public Field times(Vector that) {
//        return null;
//    }
//
//    /**
//     * Returns a copy of this vector
//     * {@link AllocatorContext allocated}
//     * by the calling thread (possibly on the stack).
//     *
//     * @return an identical and independant copy of this matrix.
//     */
//    @Override
//    public Vector copy() {
//        return null;
//    }
//}
