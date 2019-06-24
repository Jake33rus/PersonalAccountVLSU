package com.example.jake.university.profile;

public class ProfileInfo
{
   String fio;
   String uch_step;
   String uch_zv;
   String post;
   String institute;
   String cathedra;
   String ped_stazh;
   String general_stazh;
   String email;
   String tel_numb;

    public ProfileInfo(String fio, String uch_step, String uch_zv, String post, String institute,
                       String cathedra, String ped_stazh, String general_stazh, String email,
                       String tel_numb) {
        this.fio = fio;
        this.uch_step = uch_step;
        this.uch_zv = uch_zv;
        this.post = post;
        this.institute = institute;
        this.cathedra = cathedra;
        this.ped_stazh = ped_stazh;
        this.general_stazh = general_stazh;
        this.email = email;
        this.tel_numb = tel_numb;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getUch_step() {
        return uch_step;
    }

    public void setUch_step(String uch_step) {
        this.uch_step = uch_step;
    }

    public String getUch_zv() {
        return uch_zv;
    }

    public void setUch_zv(String uch_zv) {
        this.uch_zv = uch_zv;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getCathedra() {
        return cathedra;
    }

    public void setCathedra(String cathedra) {
        this.cathedra = cathedra;
    }

    public String getPed_stazh() {
        return ped_stazh;
    }

    public void setPed_stazh(String ped_stazh) {
        this.ped_stazh = ped_stazh;
    }

    public String getGeneral_stazh() {
        return general_stazh;
    }

    public void setGeneral_stazh(String general_stazh) {
        this.general_stazh = general_stazh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel_numb() {
        return tel_numb;
    }

    public void setTel_numb(String tel_numb) {
        this.tel_numb = tel_numb;
    }
}
