/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface Repository<T> {
    void Insert(String sql);
    List<T> Read(String sql);
}
