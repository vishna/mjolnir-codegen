// generated file, do not edit!

package dev.vishna.test;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public final class User implements Serializable {
   // serializable
   private static final long serialVersionUID = -2800020519567815353L;

   // members
   public String id;
   public String nickname;
   public String fullname;
   public Boolean blocked;

   // ctor
   public User() {
      // default
   }

   public User(JSONObject json) {
      try { id = json.isNull("id") ? "" : json.optString("id", ""); } catch (Throwable t) {};
      try { nickname = json.isNull("nickname") ? "" : json.optString("nickname", ""); } catch (Throwable t) {};
      try { fullname = json.isNull("fullname") ? "" : json.optString("fullname", ""); } catch (Throwable t) {};
      try { blocked = json.optBoolean("blocked", false); } catch (Throwable t) {};

   }

   public JSONObject toJSON() {
      JSONObject json = new JSONObject();

      
         try { json.put("id", id); } catch (JSONException e) {} 
         try { json.put("nickname", nickname); } catch (JSONException e) {} 
         try { json.put("fullname", fullname); } catch (JSONException e) {} 
         try { json.put("blocked", blocked); } catch (JSONException e) {} 

      return json;
   }

   public static JSONArray toJSONArray(List<User> items) {
      JSONArray array = new JSONArray();
      if (items != null) for (User item : items) {
         array.put(item.toJSON());
      }
      return array;
   }



   // static methods
   public static List<User> fromJSONArray(JSONArray array) {
      ArrayList<User> result = new ArrayList<User>();
      if (array == null || array.length() < 1)
         return result;

      int n = array.length();
      for (int i=0; i<n; i++) {
         JSONObject obj = array.optJSONObject(i);
         if (null != obj) {
            result.add(new User(obj));
         }
      }
      return result;
   }

   public static User fromJSON(JSONObject json) {
      return new User(json);
   }
}