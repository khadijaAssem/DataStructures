import eg.edu.alexu.csd.datastructure.queue.LinkedBased;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedBasedTest {
    public LinkedBased Q = new LinkedBased();
    @Before
    public void initialize(){
        Q.enqueue(5);
        Q.enqueue(7);
        Q.enqueue(9);
        Q.enqueue(10);
    }
    @Test
    public void TestSize() throws Exception{
        assertEquals("Checking Size",4,Q.size());
    }
    @Test
    public void TestAdd() throws Exception{
        Q.enqueue(0);
        assertEquals("Adding one more object to the queue",5,Q.size());
    }
    @Test
    public void TestRemove() throws Exception{
        Q.dequeue();
        assertEquals("Removing an object from the list",3,Q.size());
    }
    @Test
    public void isEmpty() {
        assertEquals("Empty testing",false,Q.isEmpty());
    }
    @Test
    public void isEmpty2() {
        Q.dequeue();
        Q.dequeue();
        Q.dequeue();
        Q.dequeue();
        assertEquals("Empty testing",true,Q.isEmpty());
    }
}