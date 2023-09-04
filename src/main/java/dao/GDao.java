package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import packOutils.Functions;

@SuppressWarnings("unchecked")
public class GDao {
        
	public static <T> List<T> findAll(Connection conn, Class<T> objClass, String table) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = GDaoUtil.prepareFindAll(conn, objClass, table);
			rs = ps.executeQuery();
			List<T> resultList = GDaoUtil.rsToList(rs, objClass);
			return resultList;
		} catch(Exception e) {
			throw e;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
		}
	}
        
	public static <T> List<T> findAll(Class<T> objClass, String table) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			List<T> result = findAll(conn, objClass, table);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	public static <T> List<T> find(Connection conn, Class<T> objClass, String table) throws Exception {
		List<T> result = findAll(conn, objClass, table);
		return result;
	}
	public static <T> List<T> find(Connection conn, Class<T> objClass, String table, String where) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = GDaoUtil.prepareFind(conn, objClass, table, where);
			rs = ps.executeQuery();
			List<T> resultList = GDaoUtil.rsToList(rs, objClass);
			return resultList;
		} catch(Exception e) {
			throw e;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
		}
	}
        
        
        
	public static <T> List<T> find(Connection conn, Class<T> objClass, String table, Map<String, Object> where) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = GDaoUtil.prepareFind(conn, objClass, table, where);
			rs = ps.executeQuery();
			List<T> resultList = GDaoUtil.rsToList(rs, objClass);
			return resultList;
		} catch(Exception e) {
			throw e;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
		}
	}
        
        public static <T> List<T> findAllMongo(Class<T> objClass, String table)throws Exception{
            return findAllMongo(objClass, table, new Document());
        }
        
        public static <T> T findByIdMongo(Class<T>  objClass, String table, String id) throws Exception{
            Document query = new Document().append("_id", new ObjectId(id));
            List<T> liste = findAllMongo(objClass, table, query);
            if(!liste.isEmpty())return liste.get(0);
            return null;
        }
        public static <T> List<T> findAllMongo(Class<T> objClass, String table, Document query)throws Exception{
        List<T> resultats = new ArrayList<T>();
        MongoCursor<Document> cursor = null;
        MongoDatabase database = null;
        MongoCollection collection = null;
        try{
            database = DbConnect.getDatabaseMongo();
            collection = database.getCollection(table);            
            cursor = collection.find(query).iterator();
            while(cursor.hasNext()){
                Document document = cursor.next();
                resultats.add(GDaoUtil.constructMongo(objClass, document));
            }
        }
        catch(Exception exception){
            throw exception;
        }
        finally{
            if(cursor!=null)cursor.close();
        }
        return resultats;
        }
	public static <T> List<T> find(Class<T> objClass, String table) throws Exception {
		List<T> result = findAll(objClass, table);
		return result;
	}
        
	public static <T> List<T> find(Class<T> objClass, String table, String where) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			List<T> result = find(conn, objClass, table, where);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
        
	public static <T> List<T> find(Class<T> objClass, String table, Map<String, Object> where) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			List<T> result = find(conn, objClass, table, where);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
        
	public static <T> T findById(Connection conn, T obj, String table) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class<T> objClass = (Class<T>) obj.getClass();
			ps = GDaoUtil.prepareFindById(conn, table, objClass, obj);
			rs = ps.executeQuery();
			List<T> resultList = GDaoUtil.rsToList(rs, objClass);
			return resultList.get(0);
		} catch(Exception e) {
			throw e;
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
		}
	}
	public static <T> T findById(T obj, String table) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			T result = findById(conn, obj, table);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
        
        public static void insertMongo(Object objet, String table) throws Exception{
            MongoDatabase database = null;
            MongoCollection collection = null;
            try{
                Document document = GDaoUtil.getInsertQueryMongo(objet);
                database = DbConnect.getDatabaseMongo();
                collection = database.getCollection(table);
                collection.insertOne(document);
            }
            catch(Exception exception){
                throw exception;
            }
            finally{
            }
        }
        /*
        public void insert(){
        MongoDatabase database = DbConnect.getDatabaseMongo();
        MongoCollection collection = database.getCollection("appel");
        Document object = new Document()
                .append("duree", this.duree)
                .append("date_appel", date_appel)
                .append("id_destinataire", id_destinataire)
                .append("id_client", id_client)
                ;
        collection.insertOne(object);
    }
        */
	private static <T> int insert(Connection conn, T obj, String table, boolean commit) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = GDaoUtil.prepareInsert(conn, obj, table);
			int result = ps.executeUpdate();
			if(commit) {
				conn.commit(); 
			}
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(ps != null) ps.close();
		}
	}
        
        public static Map<String, Object> buildMap(Class objClass, HttpServletRequest request)throws Exception{
            List<String> parameterNames = Functions.getParameterNames(request);
            Map<String, Object> map = new HashMap<String, Object>();
            Object instance = objClass.newInstance();
            for(String parameter : parameterNames){
                Method setMethod = objClass.getDeclaredMethod("set"+GDaoUtil.upperFirstCase(parameter), String.class);
                setMethod.invoke(instance, request.getParameter(parameter));
                Method getMethod = objClass.getDeclaredMethod("get"+GDaoUtil.upperFirstCase(parameter));
                Object value = getMethod.invoke(instance);
                map.put(parameter, value);
            }
            return map;
        }
        public static Document buildDocument(Class objClass, HttpServletRequest request)throws Exception{
            Document document = new Document();
            List<String> parameterNames = Functions.getParameterNames(request);
            Object instance = objClass.newInstance();
            for(String parameter : parameterNames){
                Method setMethod = objClass.getDeclaredMethod("set"+GDaoUtil.upperFirstCase(parameter), String.class);
                setMethod.invoke(instance, request.getParameter(parameter));
                Method getMethod = objClass.getDeclaredMethod("get"+GDaoUtil.upperFirstCase(parameter));
                Object value = getMethod.invoke(instance);
                document.append(parameter, value);
            }
            return document;
        }
        
        public static <T> List<T> findByMongo(Class<T> objClass, String table, HttpServletRequest request)throws Exception{
            if(!request.getParameterNames().hasMoreElements())return findAllMongo(objClass, table);
            Document query = buildDocument(objClass, request);
            return findAllMongo(objClass, table, query);
        }
        
        public static  <T> List<T> findBy(Class<T> objClass, String table, HttpServletRequest request) throws Exception{
            Map<String, Object> map = buildMap(objClass, request);
            if(!request.getParameterNames().hasMoreElements())return findAll(objClass, table);
            return find(objClass, table, map);
        }
        
	public static <T> int insert(Connection conn, T obj, String table) throws Exception {
		int result = insert(conn, obj, table, true);
		return result;
	}
	public static <T> int insert(Connection conn, T[] obj, String table) throws Exception {
		try {
			int result = 0;
			for(int i = 0; i < obj.length; i++) {
				result += insert(conn, obj[i], table, false);
			}
			conn.commit();
			return result;
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	public static int insert(Connection conn, Object[] obj) throws Exception {
		try {
			String table = "";
			int result = 0;
			for(int i = 0; i < obj.length; i++) {
				table = obj[i].getClass().getSimpleName();
				result += insert(conn, obj[i], table, false);
			}
			conn.commit();
			return result;
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	public static <T> int insert(T obj, String table) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = insert(conn, obj, table);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	public static <T> int insert(T[] obj, String table) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = insert(conn, obj, table);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	public static int insert(Object[] obj) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = insert(conn, obj);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	private static int update(Connection conn, Object obj, String table, boolean commit) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = GDaoUtil.prepareUpdate(conn, obj, table);
			int result = ps.executeUpdate();
			if(commit) {
				conn.commit(); 
			}
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(ps != null) ps.close();
		}
	}
	public static int update(Connection conn, Object obj, String table) throws Exception {
		int result = update(conn, obj, table, true);
		return result;
	}
	public static int update(Connection conn, Object[] obj, String table) throws Exception {
		try {
			int result = 0;
			for(int i = 0; i < obj.length; i++) {
				result += update(conn, obj[i], table, false);
			}
			conn.commit();
			return result;
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	public static int update(Connection conn, Object[] obj) throws Exception {
		try {
			String table = "";
			int result = 0;
			for(int i = 0; i < obj.length; i++) {
				table = obj[i].getClass().getSimpleName();
				result += update(conn, obj[i], table, false);
			}
			conn.commit();
			return result;
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	public static int update(Object obj, String table) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = update(conn, obj, table);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	public static int update(Object[] obj, String table) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = update(conn, obj, table);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	public static int update(Object[] obj) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = update(conn, obj);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	private static int delete(Connection conn, Object obj, String table, boolean commit) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = GDaoUtil.prepareDelete(conn, obj, table);
			int result = ps.executeUpdate();
			if(commit) {
				conn.commit(); 
			}
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(ps != null) ps.close();
		}
	}
	public static int delete(Connection conn, Object obj, String table) throws Exception {
		int result = delete(conn, obj, table, true);
		return result;
	}
	public static int delete(Connection conn, Object[] obj, String table) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = GDaoUtil.prepareDeleteMany(conn, obj, table);
			int result = ps.executeUpdate();
			conn.commit(); 
			return result;
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(ps != null) ps.close();
		}
	}
	public static int delete(Connection conn, Object[] obj) throws Exception {
		try {
			String table = "";
			int result = 0;
			for(int i = 0; i < obj.length; i++) {
				table = obj[i].getClass().getSimpleName();
				result += delete(conn, obj[i], table, false);
			}
			conn.commit();
			return result;
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
	}
	public static int delete(Object obj, String table) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = delete(conn, obj, table);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	public static int delete(Object[] obj, String table) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = delete(conn, obj, table);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
	public static int delete(Object[] obj) throws Exception {
		Connection conn = null;
		try {
			conn = DbConnect.getConnection();
			int result = delete(conn, obj);
			return result;
		} catch(Exception e) {
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
	}
}