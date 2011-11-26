SOC_FAMILY = "at91"

require conf/machine/include/tune-arm926ejs.inc
PREFERRED_PROVIDER_virtual/kernel = "linux-2.6.39"
# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r111"

KERNEL_IMAGETYPE = "uImage"

UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

EXTRA_IMAGEDEPENDS += "u-boots at91bootstrap"

SERIAL_CONSOLE ?= "115200 ttyS0"
EXTRA_IMAGECMD_jffs2 = "--pad --little-endian --eraseblock=0x20000 -n"

MACHINE_FEATURES = "kernel26 serial usbgadget vfat mmc alsa ext2"



