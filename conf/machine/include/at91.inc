SOC_FAMILY = "at91"

require conf/machine/include/tune-arm926ejs.inc

MACHINE_FEATURES = "kernel26 serial usbgadget vfat mmc alsa ext2"

# ROOT FIle System
# IMAGE_FSTYPES ?= "jffs2 tar.bz2 ubi"
IMAGE_FSTYPES ?= "jffs2 tar.bz2"

EXTRA_IMAGECMD_jffs2 = "--pad --little-endian --eraseblock=0x20000 -n"

# Ship all kernel modules
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules"

# If we use UBI File System
# do ubiattach /dev/ubi_ctrl -m 4
# From dmesg:
# UBI: smallest flash I/O unit:    2048
# UBI: logical eraseblock size:    129024 bytes
# from ubiattach stdout:
# UBI device number 0, total 1996 LEBs
MKUBIFS_ARGS = "-m 2048 -e 129024 -c 1996"

# do ubiattach /dev/ubi_ctrl -m 4
# from dmesg:
# UBI: smallest flash I/O unit:    2048
# UBI: physical eraseblock size:   131072 bytes (128 KiB)
# UBI: sub-page size:              512
UBINIZE_ARGS = "-m 2048 -p 128KiB -s 512"


# KERNEL
PREFERRED_PROVIDER_virtual/kernel = "linux"
# PREFERRED_PROVIDER_virtual/kernel = "linux-2.6.39"
# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r0"
KERNEL_IMAGETYPE = "uImage"


EXTRA_IMAGEDEPENDS += "u-boot at91bootstrap"

PREFERRED_PROVIDER_virtual/bootloader = "u-boot"
UBOOT_ARCH = "arm"
# UBOOT_ENTRYPOINT = "0x80008000"
# UBOOT_LOADADDRESS = "0x80008000"

PREFERRED_VERSION_at91bootstrap = "3.1.2"

SERIAL_CONSOLE ?= "115200 ttyS0"
USE_VT = "0"





