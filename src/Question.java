public class Question {
    int id, answer;
    String text, photo;

    public Question(int id, String text, String photo, int answer) {
        this.id = id;
        this.answer = answer;
        this.text = text;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return text;
    }

    public void setQuestion(String question) {
        this.text = question;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
