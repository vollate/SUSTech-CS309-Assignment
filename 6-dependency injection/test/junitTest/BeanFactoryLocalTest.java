package junitTest;

import factory.BeanFactory;
import factory.BeanFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testclass.*;


import static org.junit.jupiter.api.Assertions.*;

public class BeanFactoryLocalTest {
    private BeanFactory beanFactory;

    @BeforeEach
    public void setup() {
        this.beanFactory = new BeanFactoryImpl();
        beanFactory.loadInjectProperties("properties/private-inject.properties");
        beanFactory.loadValueProperties("properties/private-value.properties");
    }

    @Test
    public void testABResolveWithNoDependency() {
        A instanceA = beanFactory.createInstance(A.class);
        B instanceB = beanFactory.createInstance(B.class);
        assertNotNull(instanceA);
        assertNotNull(instanceB);
    }

    @Test
    public void testCTestInjectFieldClass() {
        C instanceC = beanFactory.createInstance(C.class);
        assertNotNull(instanceC.getA());
        assertNotNull(instanceC.getB());
        assertEquals("C[a=A,b=B]", instanceC.toString());
    }

    @Test
    public void testDInjectFieldValue() {
        D instanceD = beanFactory.createInstance(D.class);
        assertEquals(15, instanceD.getAge());
        assertEquals(80.5, instanceD.getMoney());
        assertEquals("liMing", instanceD.getName());
        assertTrue(instanceD.isStudent());
    }

    @Test
    public void testEInjectWithInterface() {
        E instanceE = beanFactory.createInstance(E.class);
        assertNotNull(instanceE.getC());
        assertEquals("EImpl{c=C[a=A,b=B]}", instanceE.toString());
    }

    @Test
    public void testFInjectWithExtendsAndMinMax() {
        F instanceF = beanFactory.createInstance(F.class);
        assertEquals("FEnhanced", instanceF.toString());
        assertEquals(15, instanceF.calculateNumbers());
        assertEquals("default_valuehanMei", instanceF.appendTwoStrings());
    }

    @Test
    public void testGInjectWithConstructorAndField() {
        G instanceG = beanFactory.createInstance(G.class);
        E instanceE = instanceG.getE();
        F instanceF = instanceG.getF();
        assertNotNull(instanceG);
        assertNotNull(instanceE);
        assertNotNull(instanceF);
        assertEquals("EImpl{c=C[a=A,b=B]}", instanceE.toString());
        assertEquals("FEnhanced", instanceF.toString());
        assertEquals("G{e=EImpl{c=C[a=A,b=B]},f=FEnhanced}", instanceG.toString());
    }

    @Test
    public void testHInjectWithSetMethod() {
        H instanceH = beanFactory.createInstance(H.class);
        E instanceE = instanceH.getE();
        F instanceF = instanceH.getF();
        assertNotNull(instanceH);
        assertNotNull(instanceE);
        assertNotNull(instanceF);
        assertEquals("H{e=EImpl{c=C[a=A,b=B]},f=FEnhanced,name=liMing,value=5}", instanceH.toString());
    }

    @Test
    public void testIFindInjectConstructor() {
        I instanceI = beanFactory.createInstance(I.class);
        E instanceE = instanceI.getE();
        F instanceF = instanceI.getF();
        assertNotNull(instanceI);
        assertNotNull(instanceE);
        assertNotNull(instanceF);
        assertEquals("I{e=EImpl{c=C[a=A,b=B]},f=FEnhanced}", instanceI.toString());
    }

    @Test
    public void testJFindImplAndInjectMethod() {
        J instanceJ = beanFactory.createInstance(J.class);
        E instanceE = instanceJ.getE();
        assertNotNull(instanceE);
        assertEquals(0, instanceJ.getInt());
        assertEquals("helloWorld", instanceJ.getString());
    }


}
