# X-Server ********************************************************************

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
XSERVER = "xserver-xorg \
           xf86-input-evdev \
           xf86-input-mouse \
           xf86-input-tslib \
           xf86-video-fbdev \
           xf86-input-keyboard \
	"

GUI_MACHINE_CLASS = "smallscreen"

MACHINE_FEATURES += "screen"
MACHINE_FEATURES += "touchscreen"

