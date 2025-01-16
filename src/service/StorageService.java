package service;

public interface StorageService {
	int Search(boolean disaplyOnlyOpenMediums);
	void AddMedium();
	void DeleteMedium();
}
