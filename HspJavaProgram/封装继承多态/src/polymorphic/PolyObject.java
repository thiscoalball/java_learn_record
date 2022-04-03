package polymorphic;

public class PolyObject {
        public static void main(String[] args) {

            Master tom = new Master("汤姆");
            Dog dog = new Dog("大黄~");
            Bone bone = new Bone("大棒骨~");
            tom.feed(dog, bone);

            Cat cat = new Cat("小花猫~");
            Fish fish = new Fish("黄花鱼~");
            System.out.println("===========-------");
            tom.feed(cat, fish);
            System.out.println("===================");

        }

    }

