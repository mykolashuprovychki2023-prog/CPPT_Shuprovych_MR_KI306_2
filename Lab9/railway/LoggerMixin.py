import datetime

class LoggerMixin:
    LOG_FILENAME = "log.txt"
    def log(self, message: str):
        timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        class_name = self.__class__.__name__
        entry = f"{timestamp} {class_name}: {message}"

        with open(LoggerMixin.LOG_FILENAME, "a", encoding="utf-8") as f:
            f.write(entry + "\n")