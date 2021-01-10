package com.miniprojet.reclamation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;
import static java.lang.Integer.parseInt;

/**
 * Created by Yazid on 19/01/2019.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static String DB_Name = "Reclamations";

    private static String TABLE_NAME1 = "ETUDIANT";
    private static String COL_EID = "eid";
    private static String COL_nom = "nom";
    private static String COL_prenom = "prenom";
    private static String COL_mat = "mat";
    private static String COL_mdp = "password";

    private static String TABLE_NAME2 = "RECLAMATION";
    private static String COL2_RID = "rid";
    private static String COL2_contenu = "contenu";
    private static String COL2_etat = "etat";
    private static String COL2_date_heure = "date_heure";
    private static String COL2_eid = "reclamation_eid";

    private static String TABLE_NAME3 = "GESTIONNAIRE";
    private static String COL3_GID = "gid";
    private static String COL3_pseudo = "pseudo";
    private static String COL3_nom = "nom";
    private static String COL3_prenom = "prenom";
    private static String COL3_type = "type";
    private static String COL3_mdp = "password";


    private static String TABLE_NAME4 = "GERER";
    private static String COL4_GID = "gid";
    private static String COL4_RID = "rid";
    private static String COL4_date_heure_modif = "date_heure_modif";


    private static String TABLE_NAME5 = "HISTORIQUE";
    private static String COL5_HID = "hid";
    private static String COL5_MAT = "matricule";
    private static String COL5_TEXT = "task";


    public DatabaseHandler(Context context, int version) {
        super(context, DB_Name, null, version);
    }

    /*On cree notre base de donnée*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable1 = "create table " + TABLE_NAME1 + " (" + COL_EID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " + COL_nom + " TEXT , " + COL_prenom + " TEXT , " + COL_mat + " INTEGER , " + COL_mdp + " TEXT );";
        db.execSQL(createTable1);

        String createTable2 = "create table " + TABLE_NAME2 + " (" + COL2_RID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL2_contenu + " TEXT , " + COL2_etat + " INTEGER , " + COL2_eid + " INTEGER , " + COL2_date_heure + " DATE DEFAULT CURRENT_TIMESTAMP , FOREIGN KEY (" + COL2_eid + ") REFERENCES " + TABLE_NAME1 + "(" + COL_EID + ") );";
        db.execSQL(createTable2);


        String createTable3 = "create table " + TABLE_NAME3 + " (" + COL3_GID + " INTEGER PRIMARY KEY AUTOINCREMENT , "+COL3_pseudo+" TEXT,  " + COL3_nom + " TEXT , " + COL3_prenom + " TEXT , " + COL3_type + " INTEGER , " + COL3_mdp + " TEXT );";
        db.execSQL(createTable3);


        String createTable4 = "create table " + TABLE_NAME4 + " (" + COL4_GID + " INTEGER  , " + COL4_RID + " INTEGER , " + COL4_date_heure_modif + " DATE  DEFAULT '0000-00-00 00:00:00',FOREIGN KEY (" + COL4_GID + ") REFERENCES " + TABLE_NAME3 + "(" + COL3_GID + "), FOREIGN KEY (" + COL4_RID + ") REFERENCES " + TABLE_NAME2 + "(" + COL2_RID + ") );";
        db.execSQL(createTable4);

        String createTable5 = "create table " + TABLE_NAME5 + " (" + COL5_HID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL5_MAT + " TEXT , "+ COL5_TEXT+" TEXT);";
        db.execSQL(createTable5);


    }

    /*ACtions si les tables existes deja*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE if exists " + TABLE_NAME1);
        db.execSQL("DROP TABLE if exists " + TABLE_NAME2);
        db.execSQL("DROP TABLE if exists " + TABLE_NAME3);
        db.execSQL("DROP TABLE if exists " + TABLE_NAME4);
        db.execSQL("DROP TABLE if exists " + TABLE_NAME5);

        onCreate(db);
    }
            /*fonction ajout etudiant*/
    public void ajouterEtudiant(Etudiant etud) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_nom, etud.getNom());
        cv.put(COL_prenom, etud.getPrenom());
        cv.put(COL_mat, etud.getMat());
        cv.put(COL_mdp, etud.getMdp());
        db.insert(TABLE_NAME1, null, cv);
    }






    /*fonction */
    public void ajouterProfesseur(Professeur prof) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL3_pseudo, prof.getPseudo());
        cv.put(COL3_nom, prof.getNom());
        cv.put(COL3_prenom, prof.getPrenom());
        cv.put(COL3_type, prof.getType());
        cv.put(COL3_mdp, prof.getMdp());
        db.insert(TABLE_NAME3, null, cv);
    }




    /*fonction retourne liste de tous le prof*/
    public List<Professeur> getAllProfesseurs() {
        List<Professeur> list_Professeur = new ArrayList<Professeur>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select * from " + TABLE_NAME3, null);
        while (cr.moveToNext()) {
            String pseudo = cr.getString(1);
            String nom = cr.getString(2);
            String prenom = cr.getString(3);
            String type = cr.getString(4);
            String mdp = cr.getString(5);
             Professeur professeur = new Professeur(pseudo, nom, prenom, type,mdp);
             list_Professeur.add(professeur);
        }
        cr.close();
        return list_Professeur;
    }

    public List<Etudiant> getAllStudents() {
        List<Etudiant> list_etudiants = new ArrayList<Etudiant>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select * from " + TABLE_NAME1, null);
        while (cr.moveToNext()) {
            String nom = cr.getString(1);
            String prenom = cr.getString(2);
            String pseudo = cr.getString(3);
            String mdp = cr.getString(4);

            Etudiant etudiant = new Etudiant(nom, prenom, pseudo,mdp);
            list_etudiants.add(etudiant);
        }
        cr.close();
        return list_etudiants;
    }



    /*fonction */
    public void ajouterReclamation(Reclamation rec,String eid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2_contenu, rec.getContenu());
        cv.put(COL2_etat, rec.getEtat());
        cv.put(COL2_eid,eid);
        db.insert(TABLE_NAME2, null, cv);
    }

    /*fonction qui associe chaque reclamation a un professeur unique */
     public void ajouterGerer(String rid,String gid){

         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COL4_RID,rid);
         cv.put(COL4_GID,gid);
         db.insert(TABLE_NAME4, null, cv);
     }


    /*fonction */
    public void ReglerReclamation(String rid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv2 = new ContentValues();
        cv.put(COL2_etat,"1");
        db.update(TABLE_NAME2,cv,"rid = ?",new String[] {rid+""});

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String datee= dateFormat.format(date);
        cv2.put(COL4_date_heure_modif,datee);
        db.update(TABLE_NAME4,cv2,"rid = ?",new String[] {rid+""});



    }
    /*fonction */
    public void ModifierContenuReclamation(String rid,String rec){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2_contenu,rec);
        db.update(TABLE_NAME2,cv,"rid = ?",new String[] {rid+""});
    }
    /*fonction */
    public void suppReclamation(String rid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2,"rid = ?",new String []{rid+""});

    }

    public void suppEtudiant(String eid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME1,"eid = ?",new String []{eid+""});

    }

    public void suppProfesseur(String gid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME3,"gid = ?",new String []{gid+""});

    }


    /*fonction qui retourne les reclamations en attente d'un professeur selon son ID */
    public List<ReclamationV2>getReclamationsUnresolvedProfesseurById(String gid){
        List<ReclamationV2> list_ReclamationV2 = new ArrayList<ReclamationV2>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select "+TABLE_NAME2+"."+COL2_contenu+","+TABLE_NAME2+"."+COL2_etat+","+TABLE_NAME2+"."+COL2_RID+","+TABLE_NAME2+"."+COL2_date_heure+","+TABLE_NAME2+"."+COL2_eid+" from "+TABLE_NAME4+" inner join "+TABLE_NAME2+" on "+TABLE_NAME4+"."+COL4_RID+"="+TABLE_NAME2+"."+COL2_RID+" inner join "+TABLE_NAME3+" on "+TABLE_NAME3+"."+COL3_GID+"="+TABLE_NAME4+"."+COL4_GID+" Where "+TABLE_NAME3+"."+COL3_GID+"='"+gid+"' AND "+TABLE_NAME2+"."+COL2_etat+"='1'", null);
        while (cr.moveToNext()) {
            String contenu = cr.getString(0);
            String etat=cr.getString(1);
            String rid = cr.getString(2);
            String date = cr.getString(3);
            String eid = cr.getString(4);
            String name=getNamebyRID(rid);
            ReclamationV2 recl = new ReclamationV2(contenu,etat,rid,date,name);
            list_ReclamationV2.add(recl);
        }
        cr.close();
        return list_ReclamationV2;
    }

    /*fonction qui retourne les reclamations en attente d'un etuiant selon son ID */
    public List<ReclamationV3> getReclamationsEtudiantById(String eid) {
        List<ReclamationV3> list_ReclamationV3 = new ArrayList<ReclamationV3>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select "+TABLE_NAME2+"."+COL2_RID+","+TABLE_NAME2+"."+COL2_contenu+","+TABLE_NAME2+"."+COL2_date_heure+","+TABLE_NAME2+"."+COL2_etat+" ,"+TABLE_NAME3+"."+COL3_nom+","+TABLE_NAME3+"."+COL3_prenom+" from "+TABLE_NAME1+" inner join "+TABLE_NAME2+" on "+TABLE_NAME1+"."+COL_EID+"="+TABLE_NAME2+"."+COL2_eid+" inner join "+TABLE_NAME4+" on "+TABLE_NAME2+"."+COL2_RID+"="+TABLE_NAME4+"."+COL4_RID+" inner join "+TABLE_NAME3+" on "+TABLE_NAME3+"."+COL3_GID+"="+TABLE_NAME4+"."+COL4_GID+" where "+TABLE_NAME1+"."+COL_EID+"="+eid+"", null);
        while (cr.moveToNext()) {
            String rid = cr.getString(0);
            String contenu = cr.getString(1);
            String date = cr.getString(2);
            String etat = cr.getString(3);
            String nomp = cr.getString(4);
            String prenomp = cr.getString(5);
            ReclamationV3 recl = new ReclamationV3(rid,contenu,date,etat,nomp,prenomp);
            list_ReclamationV3.add(recl);
        }
        cr.close();
        return list_ReclamationV3;
    }
    /*fonction qui retourne toutes les reclamations sur la base */
    public List<ReclamationV4> getReclamationsAdmin() {
        List<ReclamationV4> list_ReclamationV4 = new ArrayList<ReclamationV4>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select "+TABLE_NAME2+"."+COL2_RID+","+TABLE_NAME2+"."+COL2_contenu+","+TABLE_NAME2+"."+COL2_date_heure+","+TABLE_NAME2+"."+COL2_etat+" ,"+TABLE_NAME3+"."+COL3_nom+","+TABLE_NAME3+"."+COL3_prenom+","+TABLE_NAME4+"."+COL4_date_heure_modif+","+TABLE_NAME3+"."+COL3_GID+","+TABLE_NAME1+"."+COL_nom+","+TABLE_NAME1+"."+COL_prenom+" from "+TABLE_NAME1+" inner join "+TABLE_NAME2+" on "+TABLE_NAME1+"."+COL_EID+"="+TABLE_NAME2+"."+COL2_eid+" inner join "+TABLE_NAME4+" on "+TABLE_NAME2+"."+COL2_RID+"="+TABLE_NAME4+"."+COL4_RID+" inner join "+TABLE_NAME3+" on "+TABLE_NAME3+"."+COL3_GID+"="+TABLE_NAME4+"."+COL4_GID+"", null);
        while (cr.moveToNext()) {
            String rid = cr.getString(0);
            String contenu = cr.getString(1);
            String date = cr.getString(2);
            String etat = cr.getString(3);
            String nomp = cr.getString(4);
            String prenomp = cr.getString(5);
             String date_heure_modif = cr.getString(6);
             String gid = cr.getString(7);
            String nom = cr.getString(8);
             String prenom =cr.getString(9);

            ReclamationV4 recl = new ReclamationV4(rid,contenu,date,etat,nomp,prenomp,date_heure_modif,gid,nom,prenom);
            list_ReclamationV4.add(recl);
        }
        cr.close();
        return list_ReclamationV4;
    }


            /*verifier existance user dans la base*/
    public Cursor checkUsersDB(String login,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL_mat+","+COL_mdp+" from "+TABLE_NAME1+" WHERE "+COL_mat+" = '"+login+"' AND "+COL_mdp+" = '"+password+"'",null);
        return cr;
    }
    /*verifier existance prof dans la base*/
    public Cursor checkManagersDB(String login,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL3_nom+" from "+TABLE_NAME3+" WHERE "+COL3_pseudo+" = '"+login+"' AND "+COL3_mdp+" = '"+password+"'",null);
        return cr;
    }
    /*retourner l'id de luser selon son login et son mot de pass*/
    public String GetIdUserDB(String login,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL_EID+" from "+TABLE_NAME1+" WHERE "+COL_mat+" = '"+login+"'AND "+COL_mdp+" = '"+password+"'",null);
        String id=null;
        if(cr.moveToFirst())
        {
            do{
                 id=cr.getString(0);
            } while(cr.moveToNext());
        }
        return id;
    }


    /*retouner RID selon la reclam*/
    public String getRid(String reclam, String eid){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL2_RID+" from "+TABLE_NAME2+" WHERE "+COL2_contenu+" = '"+reclam+"' AND "+COL2_eid+" = '"+eid+"'",null);
        String rid=null;
        while(cr.moveToNext()){
            rid=cr.getString(0);
        }
        return rid;
    }
    /*retourner GID selon nom et prenom, on assume que les profs ont des corrdonées distinctes*/
    public String getGid(String nom, String prenom){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL3_GID+" from "+TABLE_NAME3+" WHERE "+COL3_nom+" = '"+nom+"'AND "+COL3_prenom+" = '"+prenom+"'",null);
        String gid=null;
        while(cr.moveToNext()){
            gid=cr.getString(0);
        }

        return gid;
    }
    /*retourner GID selon matricule et mot de pass*/
    public String getGidByLoginPassword(String login, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL3_GID+" from "+TABLE_NAME3+" WHERE "+COL3_pseudo+" = '"+login+"'AND "+COL3_mdp+" = '"+password+"'",null);
        String gid=null;
        while(cr.moveToNext()){
            gid=cr.getString(0);
        }

        return gid;
    }
    /*retourner EID selon matricule et mot de pass*/
    public String getEidByLoginPassword(String login, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL_EID+" from "+TABLE_NAME1+" WHERE "+COL_mat+" = '"+login+"'AND "+COL_mdp+" = '"+password+"'",null);
        String gid=null;
        while(cr.moveToNext()){
            gid=cr.getString(0);
        }

        return gid;
    }


    /*fonction qui retourne les reclamations d'un professeur selon son ID */
    public List<ReclamationV2> getReclamationsProfesseurById(String gid) {
        List<ReclamationV2> list_ReclamationV2 = new ArrayList<ReclamationV2>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select "+TABLE_NAME2+"."+COL2_contenu+","+TABLE_NAME2+"."+COL2_etat+","+TABLE_NAME2+"."+COL2_RID+","+TABLE_NAME2+"."+COL2_date_heure+","+TABLE_NAME2+"."+COL2_eid+" from "+TABLE_NAME4+" inner join "+TABLE_NAME2+" on "+TABLE_NAME4+"."+COL4_RID+"="+TABLE_NAME2+"."+COL2_RID+" inner join "+TABLE_NAME3+" on "+TABLE_NAME3+"."+COL3_GID+"="+TABLE_NAME4+"."+COL4_GID+" Where "+TABLE_NAME3+"."+COL3_GID+"='"+gid+"' AND "+TABLE_NAME2+"."+COL2_etat+"='0'", null);
        while (cr.moveToNext()) {
            String contenu = cr.getString(0);
            String etat=cr.getString(1);
            String rid = cr.getString(2);
            String date = cr.getString(3);
            String eid = cr.getString(4);
            String name=getNamebyRID(rid);
            ReclamationV2 recl = new ReclamationV2(contenu,etat,rid,date,name);
            list_ReclamationV2.add(recl);
        }
        cr.close();
        return list_ReclamationV2;
    }

    /*retourner reclamations selon la date et le EID */
    public List<ReclamationV2> GetReclByDateEid(String date,String gid) {
        List<ReclamationV2> list_ReclamationV2 = new ArrayList<ReclamationV2>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select "+TABLE_NAME2+"."+COL2_RID+","+TABLE_NAME2+"."+COL2_contenu+","+TABLE_NAME2+"."+COL2_date_heure+","+TABLE_NAME2+"."+COL2_etat+" ,"+TABLE_NAME3+"."+COL3_nom+","+TABLE_NAME3+"."+COL3_prenom+" from "+TABLE_NAME1+" inner join "+TABLE_NAME2+" on "+TABLE_NAME1+"."+COL_EID+"="+TABLE_NAME2+"."+COL2_eid+" inner join "+TABLE_NAME4+" on "+TABLE_NAME2+"."+COL2_RID+"="+TABLE_NAME4+"."+COL4_RID+" inner join "+TABLE_NAME3+" on "+TABLE_NAME3+"."+COL3_GID+"="+TABLE_NAME4+"."+COL4_GID+" where "+TABLE_NAME4+"."+COL4_GID+"="+gid+" AND "+COL2_date_heure+" like '"+date+"%'", null);
        while (cr.moveToNext()) {
            String rid = cr.getString(0);
            String contenu = cr.getString(1);
            String datee = cr.getString(2);
            String etat = cr.getString(3);
            String nomp = cr.getString(4);
            String prenomp = cr.getString(5);
            String name = getNamebyRID(rid);
            ReclamationV2 recl = new ReclamationV2(contenu,etat,rid,datee,name);
            list_ReclamationV2.add(recl);
        }
        cr.close();
        return list_ReclamationV2;
    }

    public List<ReclamationV2> GetReclByDateEtudiant(String date,String eid) {
        List<ReclamationV2> list_ReclamationV2 = new ArrayList<ReclamationV2>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select "+TABLE_NAME2+"."+COL2_RID+","+TABLE_NAME2+"."+COL2_contenu+","+TABLE_NAME2+"."+COL2_date_heure+","+TABLE_NAME2+"."+COL2_etat+" ,"+TABLE_NAME3+"."+COL3_nom+","+TABLE_NAME3+"."+COL3_prenom+" from "+TABLE_NAME1+" inner join "+TABLE_NAME2+" on "+TABLE_NAME1+"."+COL_EID+"="+TABLE_NAME2+"."+COL2_eid+" inner join "+TABLE_NAME4+" on "+TABLE_NAME2+"."+COL2_RID+"="+TABLE_NAME4+"."+COL4_RID+" inner join "+TABLE_NAME3+" on "+TABLE_NAME3+"."+COL3_GID+"="+TABLE_NAME4+"."+COL4_GID+" where "+TABLE_NAME2+"."+COL2_eid+"="+eid+" AND "+TABLE_NAME2+"."+COL2_date_heure+" like '"+date+"%'", null);
        while (cr.moveToNext()) {
            String rid = cr.getString(0);
            String contenu = cr.getString(1);
            String datee = cr.getString(2);
            String etat = cr.getString(3);
            String nomp = cr.getString(4);
            String prenomp = cr.getString(5);
            String name = getNamebyRID(rid);
            ReclamationV2 recl = new ReclamationV2(contenu,etat,rid,datee,name);
            list_ReclamationV2.add(recl);
        }
        cr.close();
        return list_ReclamationV2;
    }


            /*retourner les reclamation de tous le systeme en fonction de la date fournie*/
    public List<ReclamationV4> GetReclByDateAdmin(String date) {
        List<ReclamationV4> list_ReclamationV4 = new ArrayList<ReclamationV4>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select "+TABLE_NAME2+"."+COL2_RID+","+TABLE_NAME2+"."+COL2_contenu+","+TABLE_NAME2+"."+COL2_date_heure+","+TABLE_NAME2+"."+COL2_etat+" ,"+TABLE_NAME3+"."+COL3_nom+","+TABLE_NAME3+"."+COL3_prenom+" from "+TABLE_NAME1+" inner join "+TABLE_NAME2+" on "+TABLE_NAME1+"."+COL_EID+"="+TABLE_NAME2+"."+COL2_eid+" inner join "+TABLE_NAME4+" on "+TABLE_NAME2+"."+COL2_RID+"="+TABLE_NAME4+"."+COL4_RID+" inner join "+TABLE_NAME3+" on "+TABLE_NAME3+"."+COL3_GID+"="+TABLE_NAME4+"."+COL4_GID+" where "+COL2_date_heure+" like '"+date+"%'", null);
        while (cr.moveToNext()) {
            String rid = cr.getString(0);
            String contenu = cr.getString(1);
            String datee = cr.getString(2);
            String etat = cr.getString(3);
            String nomp = cr.getString(4);
            String prenomp = cr.getString(5);
            String name = getNamebyRID(rid);
            ReclamationV4 recl = new ReclamationV4(rid,contenu,datee,etat,nomp,prenomp,"","",name,"");
            list_ReclamationV4.add(recl);
        }
        cr.close();
        return list_ReclamationV4;
    }


 /*retourner nbr de recmation non resolues*/
    public int getNbNotResolved(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select count(*) from "+TABLE_NAME2+" WHERE "+COL2_etat+" ='0'",null);
        String contenu=null;
        while(cr.moveToNext()){
            contenu=cr.getString(0);
        }
        int nb= parseInt(contenu);
        return nb;
    }
    /*retourner nbr de recmation  resolues*/
    public int getNbResolved(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select count(*) from "+TABLE_NAME2+" WHERE "+COL2_etat+" ='1'",null);
        String contenu=null;
        while(cr.moveToNext()){
            contenu=cr.getString(0);
        }
        int nb= parseInt(contenu);
        return nb;
    }


    public String getNamebyEID(String eid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL_nom+","+COL_prenom+" from "+TABLE_NAME1+" WHERE "+COL_EID+" ='"+eid+"'",null);
        String nom=null;
        String prenom=null;
        while(cr.moveToNext()){
            nom=cr.getString(0);
            prenom=cr.getString(1);
        }
        return ""+nom+" "+prenom+"";
    }

    public String getNamebyGID(String gid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+COL3_nom+","+COL3_prenom+" from "+TABLE_NAME3+" WHERE "+COL3_GID+" ='"+gid+"'",null);
        String nom=null;
        String prenom=null;
        while(cr.moveToNext()){
            nom=cr.getString(0);
            prenom=cr.getString(1);
        }
        return ""+nom+" "+prenom+"";
    }



    public String getNamebyRID(String rid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+TABLE_NAME1+"."+COL_nom+","+TABLE_NAME1+"."+COL_prenom+" from "+TABLE_NAME1+" inner join "+TABLE_NAME2+" on "+TABLE_NAME1+"."+COL_EID+"="+TABLE_NAME2+"."+COL2_eid+" WHERE "+COL2_RID+" ='"+rid+"'",null);
        String nom=null;
        String prenom=null;
        while(cr.moveToNext()){
            nom=cr.getString(0);
            prenom=cr.getString(1);
        }
        return ""+nom+" "+prenom+"";
    }

    public String getNamePbyRID(String rid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr= db.rawQuery("select "+TABLE_NAME3+"."+COL3_nom+","+TABLE_NAME3+"."+COL3_prenom+" from "+TABLE_NAME3+" inner join "+TABLE_NAME4+" on "+TABLE_NAME4+"."+COL4_GID+"="+TABLE_NAME3+"."+COL3_GID+" WHERE "+COL4_RID+" ='"+rid+"'",null);
        String nom=null;
        String prenom=null;
        while(cr.moveToNext()){
            nom=cr.getString(0);
            prenom=cr.getString(1);
        }
        return ""+nom+" "+prenom+"";
    }



    public void AddHistoricConnex(String matricule){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String date1= dateFormat1.format(date);
        String date2= dateFormat2.format(date);
        String text = ("Vous vous etes connecté(e) le "+date1+" à "+date2+"");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL5_MAT,matricule);
        cv.put(COL5_TEXT,text);
        db.insert(TABLE_NAME5, null, cv);
    }

    public void AddHistoricSupp(String matricule,String rid,String np){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String date1= dateFormat1.format(date);
        String date2= dateFormat2.format(date);
        String text = ("Vous avez supprimé une reclamation id:"+rid+" destinée à "+np+" le "+date1+" à "+date2+"");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL5_MAT,matricule);
        cv.put(COL5_TEXT,text);
        db.insert(TABLE_NAME5, null, cv);
    }

    public void AddHistoricRegler(String matricule,String rid){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String date1= dateFormat1.format(date);
        String date2= dateFormat2.format(date);
        String ne = getNamebyRID(rid);
        String text = ("Vous avez reglé la reclamation id:"+rid+" de "+ne+" le "+date1+" à "+date2+"");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL5_MAT,matricule);
        cv.put(COL5_TEXT,text);
        db.insert(TABLE_NAME5, null, cv);
    }





    public List<History> getHistorique(String matricule) {
        List<History> list_Historique = new ArrayList<History>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select "+COL5_TEXT+" from "+TABLE_NAME5+" where "+COL5_MAT+"='"+matricule+"' order by "+COL5_HID+" DESC", null);
        while (cr.moveToNext()) {
            String text = cr.getString(0);
            History recl = new History(text);
            list_Historique.add(recl);
        }
        cr.close();
        return list_Historique;
    }


}

