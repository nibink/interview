package io.manngao;

import org.junit.Test;


public class Stage1Test {

    private Stage1 stage1 = new Stage1();

    @Test
    public void testRemove_Identical3() throws Exception {
        String result = stage1.remove("aabcccbbad");
        System.out.println("Result = " + result);
    }

    @Test
    public void testRemove_NotIdentical3() throws Exception {
        String result = stage1.remove("aabccbbad");
        System.out.println("Result = " + result);
    }

    @Test
    public void testRemove_MultiIdentical3() throws Exception {
        String result = stage1.remove("aabccccccbbad");
        System.out.println("Result = " + result);
    }

    @Test
    public void testRemove_Identical3AtLast() throws Exception {
        String result = stage1.remove("aabcccccc");
        System.out.println("Result = " + result);
    }

    @Test(expected = Exception.class)
    public void testRemove_IllegalInput() throws Exception {
        String result = stage1.remove("aabccbbad%");
        System.out.println("Result = " + result);
    }

    @Test(expected = Exception.class)
    public void testRemove_EmptyInput() throws Exception {
        String result = stage1.remove("");
        System.out.println("Result = " + result);
    }

}
