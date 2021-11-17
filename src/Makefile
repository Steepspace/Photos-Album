##
# Photos
#
# @file
# @version 0.1

JFLAGS = --module-path $(PATH_TO_FX) --add-modules javafx.controls,javafx.fxml,javafx.graphics -d ../bin
MODEL = photos/model
BINMODEL = ../bin/$(MODEL)
VIEW = photos/view
BINVIEW = ../bin/$(VIEW)
APP = photos/app
BINAPP = ../bin/$(APP)

all: $(BINAPP)/Photos.class \
     $(BINMODEL)/Photo.class \
	 $(BINMODEL)/Album.class \
	 $(BINMODEL)/Admin.class \
	 $(BINMODEL)/User.class \
	 $(BINVIEW)/BaseController.class \
	 $(BINVIEW)/LoginController.class \
	 $(BINVIEW)/Login.fxml \
	 $(BINVIEW)/AdminController.class \
	 $(BINVIEW)/Admin.fxml \
	 $(BINVIEW)/CreateUserController.class \
	 $(BINVIEW)/CreateUser.fxml \
	 $(BINVIEW)/UserController.class \
	 $(BINVIEW)/User.fxml \
	 $(BINVIEW)/CreateAlbumController.class \
	 $(BINVIEW)/CreateAlbum.fxml \
	 $(BINVIEW)/RenameAlbumController.class \
	 $(BINVIEW)/RenameAlbum.fxml \
	 $(BINVIEW)/AlbumController.class \
	 $(BINVIEW)/Album.fxml \
	 $(BINVIEW)/AddTagController.class \
	 $(BINVIEW)/AddTag.fxml \
	 $(BINVIEW)/EditCaptionController.class \
	 $(BINVIEW)/EditCaption.fxml \
	 $(BINVIEW)/CopyPhotoController.class \
	 $(BINVIEW)/CopyPhoto.fxml \
	 $(BINVIEW)/MovePhotoController.class \
	 $(BINVIEW)/MovePhoto.fxml \
	 $(BINVIEW)/SearchController.class \
	 $(BINVIEW)/Search.fxml \
	 $(BINVIEW)/SlideShowController.class \
	 $(BINVIEW)/SlideShow.fxml \

$(BINAPP)/Photos.class: $(APP)/Photos.java
	javac $(JFLAGS) $(APP)/Photos.java

$(BINMODEL)/Photo.class: $(MODEL)/Photo.java
	javac $(JFLAGS) $(MODEL)/Photo.java

$(BINMODEL)/Album.class: $(MODEL)/Album.java
	javac $(JFLAGS) $(MODEL)/Album.java

$(BINMODEL)/Admin.class: $(MODEL)/Admin.java
	javac $(JFLAGS) $(MODEL)/Admin.java

$(BINMODEL)/User.class: $(MODEL)/User.java
	javac $(JFLAGS) $(MODEL)/User.java

$(BINVIEW)/BaseController.class: $(VIEW)/BaseController.java
	javac $(JFLAGS) $(VIEW)/BaseController.java

$(BINVIEW)/LoginController.class: $(VIEW)/LoginController.java
	javac $(JFLAGS) $(VIEW)/LoginController.java

$(BINVIEW)/Login.fxml: $(VIEW)/Login.fxml
	cp $(VIEW)/Login.fxml $(BINVIEW)

$(BINVIEW)/AdminController.class: $(VIEW)/AdminController.java
	javac $(JFLAGS) $(VIEW)/AdminController.java

$(BINVIEW)/Admin.fxml: $(VIEW)/Admin.fxml
	cp $(VIEW)/Admin.fxml $(BINVIEW)

$(BINVIEW)/CreateUserController.class: $(VIEW)/CreateUserController.java
	javac $(JFLAGS) $(VIEW)/CreateUserController.java

$(BINVIEW)/CreateUser.fxml: $(VIEW)/CreateUser.fxml
	cp $(VIEW)/CreateUser.fxml $(BINVIEW)

$(BINVIEW)/UserController.class: $(VIEW)/UserController.java
	javac $(JFLAGS) $(VIEW)/UserController.java

$(BINVIEW)/User.fxml: $(VIEW)/User.fxml
	cp $(VIEW)/User.fxml $(BINVIEW)

$(BINVIEW)/CreateAlbumController.class: $(VIEW)/CreateAlbumController.java
	javac $(JFLAGS) $(VIEW)/CreateAlbumController.java

$(BINVIEW)/CreateAlbum.fxml: $(VIEW)/CreateAlbum.fxml
	cp $(VIEW)/CreateAlbum.fxml $(BINVIEW)

$(BINVIEW)/RenameAlbumController.class: $(VIEW)/RenameAlbumController.java
	javac $(JFLAGS) $(VIEW)/RenameAlbumController.java

$(BINVIEW)/RenameAlbum.fxml: $(VIEW)/RenameAlbum.fxml
	cp $(VIEW)/RenameAlbum.fxml $(BINVIEW)

$(BINVIEW)/AlbumController.class: $(VIEW)/AlbumController.java
	javac $(JFLAGS) $(VIEW)/AlbumController.java

$(BINVIEW)/Album.fxml: $(VIEW)/Album.fxml
	cp $(VIEW)/Album.fxml $(BINVIEW)

$(BINVIEW)/AddTagController.class: $(VIEW)/AddTagController.java
	javac $(JFLAGS) $(VIEW)/AddTagController.java

$(BINVIEW)/AddTag.fxml: $(VIEW)/AddTag.fxml
	cp $(VIEW)/AddTag.fxml $(BINVIEW)

$(BINVIEW)/EditCaptionController.class: $(VIEW)/EditCaptionController.java
	javac $(JFLAGS) $(VIEW)/EditCaptionController.java

$(BINVIEW)/EditCaption.fxml: $(VIEW)/EditCaption.fxml
	cp $(VIEW)/EditCaption.fxml $(BINVIEW)

$(BINVIEW)/CopyPhotoController.class: $(VIEW)/CopyPhotoController.java
	javac $(JFLAGS) $(VIEW)/CopyPhotoController.java

$(BINVIEW)/CopyPhoto.fxml: $(VIEW)/CopyPhoto.fxml
	cp $(VIEW)/CopyPhoto.fxml $(BINVIEW)

$(BINVIEW)/MovePhotoController.class: $(VIEW)/MovePhotoController.java
	javac $(JFLAGS) $(VIEW)/MovePhotoController.java

$(BINVIEW)/MovePhoto.fxml: $(VIEW)/MovePhoto.fxml
	cp $(VIEW)/MovePhoto.fxml $(BINVIEW)

$(BINVIEW)/SearchController.class: $(VIEW)/SearchController.java
	javac $(JFLAGS) $(VIEW)/SearchController.java

$(BINVIEW)/Search.fxml: $(VIEW)/Search.fxml
	cp $(VIEW)/Search.fxml $(BINVIEW)

$(BINVIEW)/SlideShowController.class: $(VIEW)/SlideShowController.java
	javac $(JFLAGS) $(VIEW)/SlideShowController.java

$(BINVIEW)/SlideShow.fxml: $(VIEW)/SlideShow.fxml
	cp $(VIEW)/SlideShow.fxml $(BINVIEW)

clean:
	rm -r ../bin/*

# end
