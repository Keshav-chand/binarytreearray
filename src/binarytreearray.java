import java.util.Scanner;

public class binarytreearray {
    static public int[] tree = new int[2], temptree;
    static public int i;
    public int size, key, index, value, a = 0, newvalue;
    static Scanner sc = new Scanner(System.in);

    public void insertvalue() {
        System.out.println("Enter the no of elements you want to insert in the array binary tree");
        size = sc.nextInt();
        tree = new int[size + 1];
        for (i = 1; i<= size; i++) {
            System.out.println("Enter the value " + (i ));
            newvalue = sc.nextInt();
            if (newvalue != 0) {
                tree[i] = newvalue;
                printtree();
            } else {
                System.out.println("Cannot use 0, reserved for other purposes. Enter any other element.");
                i--;
            }
        }
        i--; // Ensure that i is adjusted to the correct value after the loop is executed.
    }

    public void insertnewvalues() {
        System.out.println("Enter the no of elements you want to insert in the array binary tree again");
        int newSize = sc.nextInt();
        if ((i + newSize + 1) > tree.length) {
            temptree = new int[i + newSize + 2];
            System.arraycopy(tree, 0, temptree, 0, tree.length); // Copying original array to a temporary array
            tree = new int[temptree.length];
            System.arraycopy(temptree, 0, tree, 0, temptree.length); // Copying temp array back to original array
            insert(newSize);
        }
    }

    public void insert(int s) {
        while (s > 0) {
            i++;
            System.out.println("Enter the value at " + i);
            newvalue = sc.nextInt();
            if (newvalue != 0) {
                tree[i] = newvalue;
                s--;
            } else {
                System.out.println("Cannot use 0. Reserved for other purposes. Enter any other number.");
                i--;
            }
            printtree();
        }
    }

    public void deletevalue() {
        System.out.println("Enter the element you want to delete");
        key = sc.nextInt();
        if (key != 0) {
            index = searchvalue(key);
            if (index != -1) {
                if (index == i) {
                    tree[index] = 0; // Value of index set to zero, i.e., deleting the value
                    i--; // To indicate that the last element has been removed
                } else {
                    tree[index] = tree[i];
                    tree[i] = 0;
                    i--;
                }
                printtree();
                printfamily();
                System.out.println("Value found and deleted");
            } else {
                System.out.println("Value " + key + " not found");
            }
        } else {
            System.out.println("Value 0 does not exist");
        }
    }

    public int searchvalue(int key) {
        for (int j = 1; j <= i; j++) {
            if (key == tree[j]) {
                return j;
            }
        }
        return -1; // Search failed
    }

    public void printtree() {
        System.out.println("Binary tree in level order. Root is " + tree[1]);
        for (int j = 1; j <= i; j++) {
            if (tree[j] != 0) {
                System.out.print(tree[j] + " ");
            }
        }
        System.out.println("\n"); // Next line
    }

    public void printfamily() {
        System.out.println("Details of binary are:");
        for (int j = i; j > 1; j--) {
            if (tree[j] != 0) {
                System.out.println("\nParent of " + tree[j] + " is " + tree[j / 2]);
            }
            if (2 * j <= i && tree[2 * j] != 0) {
                System.out.println("\nThe left child of the tree is " + tree[2 * j]);
            }
            if (2 * j + 1 <= i && tree[2 * j + 1] != 0) {
                System.out.println("\nThe right child of the tree is " + tree[2 * j + 1]);
            }
        }
    }

    public static void main(String[] args) {
        int ch;
        String status = "c";
        binarytreearray b = new binarytreearray();
        do {
            System.out.println("0. Exit");
            System.out.println("1. Insert");
            System.out.println("2. Deletion");
            System.out.println("3. Print binary tree");
            System.out.println("Enter your choice");
            ch = sc.nextInt();
            switch (ch) {
                case 0:
                    status = "s";
                    break;
                case 1:
                    if (tree[i] != 0) {
                        b.insertnewvalues();
                    } else {
                        b.insertvalue();
                    }
                    break;
                case 2:
                    if (tree[i] != 0) {
                        b.deletevalue();
                    } else {
                        System.out.println("Tree is empty");
                    }
                    break;
                case 3:
                    if (tree[i] != 0) {
                        b.printtree();
                        b.printfamily();
                    } else {
                        System.out.println("Tree is empty");
                    }
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        } while (!status.equals("s"));
        System.out.println("Program completed successfully");
    }
}
