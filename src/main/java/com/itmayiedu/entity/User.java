package com.itmayiedu.entity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by YJJ on 2019/9/24.
 */
public class User {
    int id;
    String name;
    String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void get(){
        return ;
    }


    //测试CopyOnWriteArrayList
    //region
    /*public static void main(String[] args) {
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
        list.add("1");list.add("2");list.add("3");list.add("4");list.add("5");
        Iterator<String> iterator=list.iterator();

        Thea t=new Thea(list);
        t.start();

        synchronized (list) {
            while(iterator.hasNext()){
                String i=iterator.next();

                System.out.println(i);
                if (i.equals("3")){
                    list.remove(3);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println(list.size());
    }
    static class Thea extends Thread{
        private CopyOnWriteArrayList list;

        public Thea(CopyOnWriteArrayList list) {
            this.list = list;
        }

        @Override
        public void run() {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (list) {
                Iterator i=list.iterator();
                while (i.hasNext()){
                    System.out.println("向城外"+i.next());
                }
            }
        }
    }*/
    //endregion
}
