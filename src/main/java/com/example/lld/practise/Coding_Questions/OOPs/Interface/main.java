package com.example.lld.practise.Coding_Questions.OOPs.Interface;

public class main {
    public static void main(String[] args) {
        Test2 t2 = new Test2();
        t2.show();

        // means if a class extends a parent class and also implement an interface then, if Both parent class and interface has same method
        // and if we call class.method() then it will give priority to parent class and not interface.
    }
}
