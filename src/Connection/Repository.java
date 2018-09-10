/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface Repository<T> {
    void Insert(T item);
    List<? extends T> Read(String sql);
    List<T> ReadAll();
}
