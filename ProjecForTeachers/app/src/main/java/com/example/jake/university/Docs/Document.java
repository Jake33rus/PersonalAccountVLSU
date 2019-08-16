package com.example.jake.university.Docs;

public class Document
{
    private String docDate;
    private String docNumb;
    private  String docTitle;
    private String begDate;
    private String endDate;
    private String typeId;
    private String docTypeName;
    private String docStatusId;
    private String docStatusName;
    private String descr;
    private String creatorFIO;
    private String titleAndNumber;
    private String template;
    private String fileName;
    private String author;
    private String isViewd;
    private String ID;

    public Document(String docDate, String docNumb, String docTitle, String begDate,
                    String endDate, String typeId, String docTypeName, String docStatusId,
                    String docStatusName, String descr, String creatorFIO, String titleAndNumber, String template,
                    String fileName, String author, String isViewd, String ID)
    {
        this.docDate = docDate;
        this.docNumb = docNumb;
        this.docTitle = docTitle;
        this.begDate = begDate;
        this.endDate = endDate;
        this.typeId = typeId;
        this.docTypeName = docTypeName;
        this.docStatusId = docStatusId;
        this.docStatusName = docStatusName;
        this.descr = descr;
        this.creatorFIO = creatorFIO;
        this.titleAndNumber = titleAndNumber;
        this.template = template;
        this.fileName = fileName;
        this.author = author;
        this.isViewd = isViewd;
        this.ID = ID;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getDocNumb() {
        return docNumb;
    }

    public void setDocNumb(String docNumb) {
        this.docNumb = docNumb;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getBegDate() {
        return begDate;
    }

    public void setBegDate(String begDate) {
        this.begDate = begDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public String getDocStatusId() {
        return docStatusId;
    }

    public void setDocStatusId(String docStatusId) {
        this.docStatusId = docStatusId;
    }

    public String getDocStatusName() {
        return docStatusName;
    }

    public void setDocStatusName(String docStatusName) {
        this.docStatusName = docStatusName;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCreatorFIO() {
        return creatorFIO;
    }

    public void setCreatorFIO(String creatorFIO) {
        this.creatorFIO = creatorFIO;
    }

    public String getTitleAndNumber() {
        return titleAndNumber;
    }

    public void setTitleAndNumber(String titleAndNumber) {
        this.titleAndNumber = titleAndNumber;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsViewd() {
        return isViewd;
    }

    public void setIsViewd(String isViewd) {
        this.isViewd = isViewd;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
