package io.manngao;

import org.junit.Test;


public class Stage2Test {

    private Stage2 stage2 = new Stage2();

    @Test
    public void testReplace_Identical3() throws Exception {
        String result = stage2.replace("abcccbad");
        System.out.println("Result = " + result);
    }

    @Test
    public void testReplace_NotIdentical3() throws Exception {
        String result = stage2.replace("aabccbbad");
        System.out.println("Result = " + result);
    }

    @Test
    public void testReplace_MultiIdentical3() throws Exception {
        String result = stage2.replace("aabccccccbbad");
        System.out.println("Result = " + result);
    }

    @Test
    public void testReplace_Identical3AtLast() throws Exception {
        String result = stage2.replace("aabcccccc");
        System.out.println("Result = " + result);
    }

    @Test(expected = Exception.class)
    public void testReplace_IllegalInput() throws Exception {
        String result = stage2.replace("aabccbbad%");
        System.out.println("Result = " + result);
    }

    @Test(expected = Exception.class)
    public void testReplace_EmptyInput() throws Exception {
        String result = stage2.replace("");
        System.out.println("Result = " + result);
    }

}
