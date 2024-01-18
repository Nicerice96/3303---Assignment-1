public class Box {

    /**
     * The object stored in this Box.
     */
    private Object contents = null;

    /**
     * State of the box.
     */
    private boolean empty = true;

    /**
     * Stores its argument in the Box if the Box is empty;
     * otherwise, the Box contents are not changed.
     *
     * @param obj the object that is to be stored in this Box.
     * @return true if obj was stored in this Box; false if another object
     *          was already stored in the Box (in which case, this Box was not
     *          changed by invoking this method).
     */
    public synchronized void put(Object obj) {
        // Wait for the Box to be empty; ie it's full, therefore we cannot produce
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        // This Box is empty, so store obj.
        contents = obj;
        empty = false; // Mark the box as full.
        notifyAll();
    }

    /**
     * Removes the object stored in this Box, leaving the Box empty.
     * @return the object stored in this Box, if there is one.
     *          If the Box is empty, returns null.
     */

    public synchronized Object get() {
        // Wait for the Box to full (not empty)
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        // Mark the box as empty.
        Object obj = contents;
        empty = true;
        notifyAll();

        return obj;
    }
}
