module com.example.texttospeechfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires gax;
    requires com.google.auth.oauth2;
    requires proto.google.cloud.speech.v1;
    requires protobuf.java;
    requires google.cloud.speech;


    opens com.example.texttospeechfx to javafx.fxml;
    exports com.example.texttospeechfx;
}