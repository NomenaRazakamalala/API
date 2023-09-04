package dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import annotations.DbIgnore;
import org.bson.Document;

@SuppressWarnings("unchecked")
public class GDaoUtil {
	static <T> PreparedStatement prepareFindAll(Connection conn, Class<T> objClass, String table) throws Exception {
		String select = "";
		List<Field> fields = getFields(objClass);
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0) {
				select += ", ";
			}
			select += fields.get(i).getName();
		}
		String request = "select " + select + " from " + table;
		PreparedStatement ps = conn.prepareStatement(request);
		return ps;
	}
	private static int linkKey(String key, String[] str) {
		int beginIndexKey = 0;
		str[0] = "and";
		String[] links = new String[] {"and ", "AND ", "or", "OR"};
		for(String link : links) {
			if(key.startsWith(link)) {
				str[0] = link.toLowerCase();
				beginIndexKey = link.length();
			}
		}
		return beginIndexKey;
	}
	private static int comparatorKey(String key, String[] str) {
		int endIndexKey = key.length();
		str[2] = "=";
		String[] comparators = new String[] {"=", "<", ">", "<=", ">=", "like"};
		for(String comparator : comparators) {
			if(key.endsWith(comparator)) {
				str[2] = comparator;
				endIndexKey = key.length() - comparator.length();
			}
		}
		return endIndexKey;
	}
	private static String[] separeOperator(String key) {
		key = key.trim();
		String[] str = new String[3];
		int beginIndexKey = linkKey(key, str);
		int endIndexKey = comparatorKey(key, str);
		str[1] = key.substring(beginIndexKey, endIndexKey).trim();
		return str;
	}
	private static String getWhereOnFind(Map<String, Object> where) {
		String condition = " where";
		String[] str = null;
		int i = 0;
		for(String key : where.keySet()) {
			str = separeOperator(key);
			if(i > 0) {
				condition += " " + str[0];
			}
			condition += " " + str[1];
			condition += " " + str[2];
			condition += " ?";
			/*
			 * apdirina n cle
			 * alaiko le comparateur
			 * d asina "?"
			 */
			i += 1;
		}
		return condition;
	}
	private static void setPS(PreparedStatement ps, int i, String type, Object value) throws Exception {
		i += 1;
		if(value == null) {
			ps.setObject(i, value);
		} else if(type.equals("int") || type.equals("Integer")) {
			ps.setInt(i, (Integer) value);
		} else if(type.equals("float") || type.equals("Float")) {
			ps.setFloat(i, (Float) value);
		} else if(type.equals("double") || type.equals("Double")) {
			ps.setDouble(i, (Double) value);
		} else if(type.equals("boolean") || type.equals("Boolean")) {
			ps.setBoolean(i, (Boolean) value);
		} else if(type.equals("String")) {
			ps.setString(i, (String) value);
		} else if(type.equals("Date")) {
			ps.setDate(i, (Date) value);
		} else if(type.equals("Timestamp")) {
			ps.setTimestamp(i, (Timestamp) value);
		} else if(type.equals("Time")) {
			ps.setTime(i, (Time) value);
		} else if(type.equals("LocalDate")) {
			ps.setDate(i, Date.valueOf((LocalDate) value));
		} else if(type.equals("LocalTime")) {
			ps.setTime(i, Time.valueOf((LocalTime) value));
		} else if(type.equals("LocalDateTime")) {
			ps.setTimestamp(i, Timestamp.valueOf((LocalDateTime) value));
		}
	}
	private static void setWhereValuesOnFind(PreparedStatement ps, Map<String, Object> where) throws Exception {
		String type = "";
		Object value = null;
		int i = 0;
		for(Entry<String, Object> entry : where.entrySet()) {
			value = entry.getValue();
			type = value.getClass().getSimpleName();
			setPS(ps, i, type, value);
			i += 1;
		}
	}
	static <T> PreparedStatement prepareFind(Connection conn, Class<T> objClass, String table, String where) throws Exception {
		String select = "";
		List<Field> fields = getFields(objClass);
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0) {
				select += ", ";
			}
			select += fields.get(i).getName();
		}
		String request = "select " + select + " from " + table;
		if(where != null) {
			request += " where " + where;
		}
		PreparedStatement ps = conn.prepareStatement(request);
		return ps;
	}
	static <T> PreparedStatement prepareFind(Connection conn, Class<T> objClass, String table, Map<String, Object> where) throws Exception {
		boolean hasWhere = where != null;
		String select = "";
		List<Field> fields = getFields(objClass);
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0) {
				select += ", ";
			}
			select += fields.get(i).getName();
		}
		String request = "select " + select + " from " + table;
		if(hasWhere) {
			request += getWhereOnFind(where);
		}
		PreparedStatement ps = conn.prepareStatement(request);
		if(hasWhere) {
			setWhereValuesOnFind(ps, where);
		}
		return ps;
	}
	static <T> PreparedStatement prepareFindById(Connection conn, String table, Class<T> objClass, T obj) throws Exception {
		String select = "";
		List<Field> fields = getFields(objClass);
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0) {
				select += ", ";
			}
			select += fields.get(i).getName();
		}
		String request = "select " + select + " from " + table  + " where id like ?";
		Method idMethod = objClass.getDeclaredMethod("getId");
		String id = (String) idMethod.invoke(obj);
		PreparedStatement ps = conn.prepareStatement(request);
		ps.setString(1, id);
		return ps;
	}
	private static boolean isPKSequence(String field) {
		if(field.toLowerCase().equals("id")) {
			return true;
		}
		return false;
	}
	private static String setInsertRequest(String table, List<Field> fields) {
		String request = "", strFields = "(", strValues = "(";
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0) {
				strFields += ", ";
				strValues += ", ";
			}
			strFields += fields.get(i).getName();
			if(isPKSequence(fields.get(i).getName())) {
				strValues += DbConnect.getSequenceTable(table);
			} else {
				strValues += "?";
			}
		}
		strFields += ")";
		strValues += ")";
		request += "insert into " + table + " " + strFields + " values " + strValues;
		return request;
	}
	private static void setInsertValues(PreparedStatement ps, Object obj, List<Field> fields, List<Method> methods) throws Exception {
		String type = "";
		Object value = null;
		int j = 0;
		for(int i = 0; i < fields.size(); i++) {
			if(!isPKSequence(fields.get(i).getName())) {
				type = fields.get(i).getType().getSimpleName();
				value = methods.get(i).invoke(obj);
				setPS(ps, j, type, value);
				j += 1;
			}
		}
	}
	static <T> PreparedStatement prepareInsert(Connection conn, T obj, String table) throws Exception {
		Class<T> objClass = (Class<T>) obj.getClass();
		List<Field> fields = getFields(objClass);
		List<Method> methods = getGettersMehods(objClass);
		String request = setInsertRequest(table, fields);
		PreparedStatement ps = conn.prepareStatement(request);
		setInsertValues(ps, obj, fields, methods);
		return ps;
	}
	private static String setUpdateRequest(String table, List<Field> fields) throws Exception {
		String request = "update " + table + " set " ;
		String where = " where ";
		int i = 0;
		for(Field field : fields) {
			if(i > 0) {
				request += ", ";
			}
			if(isPKSequence(field.getName())) {
				where += field.getName() + " = ?";
			} else {
				request += field.getName() + " = ?";
				i += 1;
			}
		}
		request += where;
		System.out.println(request);
		return request;
	}
	private static void setUpdateValues(PreparedStatement ps, Object obj, List<Field> fields, List<Method> methods) throws Exception {
		String type = "";
		Object value = null;
		int j = 0;
		for(int i = 0; i < fields.size(); i++) {
			if(!isPKSequence(fields.get(i).getName())) {
				type = fields.get(i).getType().getSimpleName();
				value = methods.get(i).invoke(obj);
				setPS(ps, j, type, value);
				j += 1;
			} else {
				type = fields.get(i).getType().getSimpleName();
				value = methods.get(i).invoke(obj);
				setPS(ps, fields.size() - 1, type, value);
			}
		}
	}
	@SuppressWarnings("rawtypes")
	static PreparedStatement prepareUpdate(Connection conn, Object obj, String table) throws Exception {
		Class objClass = obj.getClass();
		List<Field> fields = getFields(objClass);
		List<Method> methods = getGettersMehods(objClass);
		String request = setUpdateRequest(table, fields);
		PreparedStatement ps = conn.prepareStatement(request);
		setUpdateValues(ps, obj, fields, methods);
		return ps;
	}
	static <T> PreparedStatement prepareDelete(Connection conn, T obj, String table) throws Exception {
		String request = "delete from " + table + " where id = ?";
		Class<T> objClass = (Class<T>) obj.getClass();
		String id = (String) objClass.getDeclaredMethod("getId").invoke(obj);
		PreparedStatement ps = conn.prepareStatement(request);
		ps.setString(1, id);
		return ps;
	}
	private static String setDeleteManyRequest(String table, Method method, int n) throws Exception {
		String request = "delete from " + table + " where id in (";
		for(int i = 0; i < n; i++) {
			if(i > 0) {
				request += ", ";
			}
			request += "?";
		}
		request += ")";
		return request;
	}
	public static <T> void setDeleteManyValues(PreparedStatement ps, Method method, T[] obj, int n)  throws Exception {
		String id = "";
		for(int i = 0; i < n; i++) {
			id = (String) method.invoke(obj[i]);
			ps.setString(i + 1, id);
		}
	}
	static <T> PreparedStatement prepareDeleteMany(Connection conn, T[] obj, String table) throws Exception {
		String request = "";
		Class<T> objClass = (Class<T>) obj[0].getClass();
		int n = obj.length;
		Method method = objClass.getDeclaredMethod("getId");
		request = setDeleteManyRequest(table, method, n);
		System.out.println(request);
		PreparedStatement ps = conn.prepareStatement(request);
		setDeleteManyValues(ps, method, obj, n);
		return ps;
	}
	static String upperFirstCase(String str) {
		char[] car = str.toCharArray();
        car[0] = Character.toUpperCase(car[0]);
        return new String(car);
	}
        static String lowerFirstCase(String str) {
		char[] car = str.toCharArray();
        car[0] = Character.toLowerCase(car[0]);
        return new String(car);
	}
        
	private static <T> List<Field> getFields(Class<T> objClass) throws Exception {
		List<Field> result = new ArrayList<Field>();
		Field[] fields = null;
		for(Class<?> c = objClass; c != null; c = c.getSuperclass()) {
			fields = c.getDeclaredFields();
			for(Field field : fields) {
				if(!field.isAnnotationPresent(DbIgnore.class)) {
					result.add(field);
				}
			}
		}
		return result;
	}
	private static <T> List<Method> getGettersMehods(Class<T> objClass) throws Exception {
		List<Method> result = new ArrayList<Method>();
		Field[] fields = null;
		String methodName = "";
		for(Class<?> c = objClass; c != null; c = c.getSuperclass()) {
			fields = c.getDeclaredFields();
			for(int i = 0; i < fields.length; i++) {
				if(!fields[i].isAnnotationPresent(DbIgnore.class)) {
					methodName = "get" + upperFirstCase(fields[i].getName());
					result.add(c.getDeclaredMethod(methodName));
				}
			}
		}
		return result;
	}
	static <T> List<Method> getSettersMehods(Class<T> objClass) throws Exception {
		List<Method> result = new ArrayList<Method>();
		Field[] fields = null;
		String methodName = "";
		for(Class<?> c = objClass; c != null; c = c.getSuperclass()) {
			fields = c.getDeclaredFields();
			for(int i = 0; i < fields.length; i++) {
				if(!fields[i].isAnnotationPresent(DbIgnore.class)) {
					methodName = "set" + upperFirstCase(fields[i].getName());
					result.add(c.getDeclaredMethod(methodName, fields[i].getType()));
				}
			}
		}
		return result;
	}
        
        public static <T> T constructMongo(Class<T> objClass, Document document)throws Exception{
            List<Field> fields = getFields(objClass);
            return constructMongo(objClass, document, fields);
        }       
                
        public static Document getInsertQueryMongo(Object objet) throws Exception{
            Document document = new Document();
            List<Field> fields = GDaoUtil.getFields(objet.getClass());
            String getMethod = "";
            Method methode = null;
            Object valeur = null;
            int iField = 0;
            if(!fields.isEmpty()){
                if(fields.get(0).getName().equalsIgnoreCase("id")) 
                    iField = 1;
            }
            while(iField<fields.size()){
                getMethod = "get"+upperFirstCase(fields.get(iField).getName());
                methode = objet.getClass().getDeclaredMethod(getMethod);
                valeur = methode.invoke(objet);
                document.append(fields.get(iField).getName(), valeur);
                iField++;
            }
            return document;
        }
        
        public static <T> T constructMongo(Class<T> objClass, Document document, List<Field> fields) throws Exception{
            T resultat = objClass.getConstructor().newInstance();
            String type = "";
            for(Field field : fields){
                Method setMethod = objClass.getDeclaredMethod("set"+upperFirstCase(field.getName()), field.getType());
                type = setMethod.getParameterTypes()[0].getSimpleName();
                String fieldName = field.getName();
                Object objet = document.get(fieldName);
                if(fieldName.equalsIgnoreCase("id"))fieldName="_id";
                if(objet == null){
                    setMethod.invoke(resultat, objet);
                }
                else if(type.equals("int") || type.equals("Integer")) {
                        setMethod.invoke(resultat, new Integer(document.get(fieldName).toString()));
                } else if(type.equals("float") || type.equals("Float")) {
                        setMethod.invoke(resultat, new Float(document.get(fieldName).toString()));
                } else if(type.equals("double") || type.equals("Double")) {
                        setMethod.invoke(resultat, new Double(document.get(fieldName).toString()));
                } else if(type.equals("boolean") || type.equals("Boolean")) {
                        setMethod.invoke(resultat, document.getBoolean(fieldName));
                } else if(type.equals("String")) {
                        setMethod.invoke(resultat, document.getString(fieldName));
                } else if(type.equals("Date")) {
                        setMethod.invoke(resultat, document.getDate(fieldName));
                } else if(type.equals("Timestamp")) {
                        setMethod.invoke(resultat, new Timestamp(document.getDate(fieldName).getTime()));
                }
            }
            return resultat;
        }
        
	static <T> List<T> rsToList(ResultSet rs, Class<T> objClass) throws Exception {
		List<T> resultList = new ArrayList<T>();
		List<Method> methods = getSettersMehods(objClass);
		T resultTemp = null;
		String type = "";
		while(rs.next()) {
			resultTemp = objClass.getConstructor().newInstance();
			for(int i = 0; i < methods.size(); i++) {
				type = methods.get(i).getParameterTypes()[0].getSimpleName();
				if(rs.getObject(i+1) == null) {
					methods.get(i).invoke(resultTemp, rs.getObject(i+1));
				} else if(type.equals("int") || type.equals("Integer")) {
					methods.get(i).invoke(resultTemp, rs.getInt(i+1));
				} else if(type.equals("float") || type.equals("Float")) {
					methods.get(i).invoke(resultTemp, rs.getFloat(i+1));
				} else if(type.equals("double") || type.equals("Double")) {
					methods.get(i).invoke(resultTemp, rs.getDouble(i+1));
				} else if(type.equals("boolean") || type.equals("Boolean")) {
					methods.get(i).invoke(resultTemp, rs.getBoolean(i+1));
				} else if(type.equals("String")) {
					methods.get(i).invoke(resultTemp, rs.getString(i+1));
				} else if(type.equals("Date")) {
					methods.get(i).invoke(resultTemp, rs.getDate(i+1));
				} else if(type.equals("Timestamp")) {
					methods.get(i).invoke(resultTemp, rs.getTimestamp(i+1));
				} else if(type.equals("Time")) {
					methods.get(i).invoke(resultTemp, rs.getTime(i+1));
				} else if(type.equals("LocalTime")) {
					java.sql.Time temp = rs.getTime(i+1);
					methods.get(i).invoke(resultTemp, temp.toLocalTime());
				} else if(type.equals("LocalDate")) {
					java.sql.Date temp = rs.getDate(i+1);
					methods.get(i).invoke(resultTemp, temp.toLocalDate());
				} else if(type.equals("LocalDateTime")) {
					java.sql.Timestamp temp = rs.getTimestamp(i+1);
					methods.get(i).invoke(resultTemp, temp.toLocalDateTime());
				}
			}
			resultList.add(resultTemp);
		}
		return resultList;
	}
}
