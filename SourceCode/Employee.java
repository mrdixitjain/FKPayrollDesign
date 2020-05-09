package project;

class Employee {
    public static enum MethodOfPayment {Paycheck, PaycheckPickup, BankTransfer};

    MethodOfPayment mop;
    int id;
    String name;

    public Employee(int id, String name, MethodOfPayment mop) {
        this.mop = mop;
        this.id = id;
        this.name = name;
    }
}