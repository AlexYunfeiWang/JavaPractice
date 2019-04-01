import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuadTree {

    /*
    The QuadTree will look like:
      |
    1 | 0
  ---------
    2 | 3
      |
     */

    private int MAX_OBJECTS = 10; //how many objects a node can hold before split
    private int MAX_LEVELS = 5;//deepest level subnode

    private int level; //current node level
    private List<Rectangle> objects; //objects contained in current QuadTree node
    private Rectangle bounds; //represents 2D space that the node occupies
    private QuadTree[] children; // 4 child children

    public QuadTree(int level, Rectangle bounds) {
        this.level = level;
        this.bounds = bounds;
        objects = new ArrayList<Rectangle>();
        children = new QuadTree[4];
    }

    //clears the quadtree by recursively clearing all objects from all children.
    public void clear() {
        objects.clear();
        for (int i = 0; i < children.length; ++i) {
            if (children[i] != null) {
                children[i].clear();
                children[i] = null;
            }
        }
    }

    //splits the node into 4 subnodes
    private void split() {
        int subWidth = (int)(bounds.getWidth()/2);
        int subHeight = (int)(bounds.getHeight()/2);
        int x = (int)bounds.getX();
        int y = (int)bounds.getY();

        children[0] = new QuadTree(level+1, new Rectangle(x+subWidth, y, subWidth, subHeight));
        children[1] = new QuadTree(level+1, new Rectangle(x, y, subWidth, subHeight));
        children[2] = new QuadTree(level+1, new Rectangle(x, y+subHeight, subWidth, subHeight));
        children[3] = new QuadTree(level+1, new Rectangle(x+subWidth, y+subHeight, subWidth, subHeight));
    }

    /*
     * Determine which node the object belongs to. -1 means
     * object cannot completely fit within a child node and is part
     * of the parent node
     */
    private int getIndex(Rectangle pRect) {
        int index = -1;
        double verticalMid = bounds.getX() + (bounds.getWidth()/2);
        double horizontalMid = bounds.getY() + (bounds.getHeight()/2);

        // Object can completely fit within the top quadrant
        boolean topQuad = (pRect.getY() < horizontalMid && pRect.getY() + pRect.getHeight() < horizontalMid);

        //Object can completely fit within the bottom quadrant
        boolean bottomQuad = (pRect.getY() > horizontalMid);

        //Object can completely fit within left quandrant
        boolean leftQuad = (pRect.getX() < verticalMid && pRect.getX() + pRect.getWidth() < verticalMid);

        //Object can completely fit within left quandrant
        boolean rightQuad = (pRect.getX() > verticalMid);

        if (leftQuad) {
            if (topQuad) {
                index = 1;
            }
            else if (bottomQuad) {
                index = 2;
            }
        }
        else if (rightQuad) {
            if (topQuad) {
                index = 0;
            }
            else if (bottomQuad) {
                index = 3;
            }
        }
        return index;
    }

    /*
     * Insert the object into the quadtree. If the node
     * exceeds the capacity, it will split and add all
     * objects to their corresponding children.
     */
    private void insert(Rectangle pRect) {
        //try to insert object into any of the child nodes
        if (children[0] != null) {
            int index = getIndex(pRect);
            if (index != -1) {
                children[index].insert(pRect);
                return;
            }
        }
        /*
        If there are no child nodes or the object doesn’t fit in a child node,
        it adds the object to the parent node.
         */
        objects.add(pRect);

        /*
        Once the object is added, it determines whether the node needs to split by
        checking if the current number of objects exceeds the max allowed objects.
        Splitting will cause the node to insert any object that can fit in a child
        node to be added to the child node; otherwise the object will stay in the
        parent node.
         */
        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
            if (children[0] != null) {
                split();
            }
            int i = 0;
            while(i < objects.size()) {
                int objIndex = getIndex(objects.get(i));
                if (objIndex != -1) {
                    children[objIndex].insert(objects.remove(i));
                }
                else {
                    i++;
                }
            }
        }
    }

    /*
     * Return all objects that could collide with the given object
     */
    public List<Rectangle> retrieveCollision(List<Rectangle> returnObjects, Rectangle pRect) {
        int index = getIndex(pRect);
        if (index != -1 && children[0] != null) {
            children[index].retrieveCollision(returnObjects, pRect);
        }
        returnObjects.addAll(objects);
        return returnObjects;
    }
}
