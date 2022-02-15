// MyAidl.aidl
package com.example.aidl_server;
import com.example.aidl_server.Book;
// Declare any non-default types here with import statements

interface MyAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    void test_Service();

    List<Book> getBooks();

     Book addBook(in Book book);


}